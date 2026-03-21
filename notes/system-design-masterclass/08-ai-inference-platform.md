# Phase 3 — Real World: Design an AI Inference Platform
> Case study: "Design the backend for a ChatGPT-like API — production grade, 10M calls/day"

This is the most important AI system to understand in 2024–2026.
Every AI product is built on an inference platform.

---

## Requirements Clarification

### Functional Requirements
- Accept chat completion requests (multi-turn conversations)
- Stream tokens as they are generated (SSE)
- Support multiple models (GPT-4 size, GPT-3.5 size)
- Manage API keys, rate limits, and usage billing
- Store conversation history for multi-turn context

### Non-Functional Requirements
```
Availability:      99.9% (8.7 hours downtime/year acceptable)
Latency:           < 500ms to first token, < 100ms per token streaming
Throughput:        10M calls/day avg, 100k req/min peak
Data Durability:   Conversation history: 30 days hot, 1 year archive
Security:          API key auth, TLS, no PII stored in logs
Scale:             Must support 10x growth in 6 months
```

### Back-of-Envelope

```
10M calls/day:
  avg req/sec = 10M / 86,400 ≈ 116 req/sec
  peak req/sec = 116 × 10 = ~1,200 req/sec

Per request tokens:
  avg prompt:   500 tokens
  avg response: 800 tokens
  total:        1,300 tokens/request

Daily token volume:
  10M × 1,300 = 13 billion tokens/day

Inference compute needed:
  7B model on A100: ~100 req/sec
  70B model on A100: ~10 req/sec (requires tensor parallelism)
  For 1,200 peak req/sec (7B model): ~12 A100s
  For 1,200 peak req/sec (70B model): ~120 A100s

Conversation storage:
  1 conversation ≈ 8 KB (messages JSON)
  10M calls/day × 8 KB = 80 GB/day
  30 days hot = 2.4 TB
```

---

## HLD: The Architecture

```
                          ┌─────────────────────────────────────┐
                          │         API CONSUMERS               │
                          │  (Developer apps / SaaS products)   │
                          └──────────────┬──────────────────────┘
                                         │ HTTPS
                          ┌──────────────▼──────────────────────┐
                          │            EDGE LAYER               │
                          │   Cloudflare (DDoS, TLS, CDN edge)  │
                          │   Global Anycast routing            │
                          └──────────────┬──────────────────────┘
                                         │
             ┌───────────────────────────▼───────────────────────────────┐
             │                    API GATEWAY                            │
             │    ┌────────────────┐  ┌──────────────┐  ┌────────────┐  │
             │    │  Auth Service  │  │ Rate Limiter │  │  Router    │  │
             │    │  (API key →    │  │ (Redis sliding│  │  (model +  │  │
             │    │   user/limits) │  │  window)     │  │  region)   │  │
             │    └────────────────┘  └──────────────┘  └────────────┘  │
             └───────────────────────────┬───────────────────────────────┘
                                         │
                     ┌───────────────────┴──────────────────┐
                     │                                      │
          ┌──────────▼─────────┐               ┌───────────▼──────────────┐
          │  CONTEXT SERVICE   │               │   INFERENCE ORCHESTRATOR  │
          │  ─────────────────  │               │   ────────────────────── │
          │  Redis cluster      │               │   Model selection        │
          │  - Fetch history   │               │   Worker pool mgmt       │
          │  - Trim to tokens  │               │   Queue management       │
          │  - Cache context   │               │   Retry + fallback       │
          └──────────┬─────────┘               └───────────┬──────────────┘
                     │                                      │
                     └───────────────┬──────────────────────┘
                                     │
             ┌───────────────────────▼───────────────────────────────────┐
             │                  GPU INFERENCE CLUSTER                    │
             │                                                           │
             │  ┌────────────┐  ┌────────────┐  ┌────────────┐         │
             │  │ Worker-01  │  │ Worker-02  │  │ Worker-N   │         │
             │  │ vLLM/TGI   │  │ vLLM/TGI   │  │ vLLM/TGI   │         │
             │  │ A100 80GB  │  │ A100 80GB  │  │ A100 80GB  │         │
             │  │ KV Cache   │  │ KV Cache   │  │ KV Cache   │         │
             │  └────────────┘  └────────────┘  └────────────┘         │
             │                                                           │
             │  Model Weights (shared, memory-mapped from NFS/S3)       │
             └───────────────────────┬───────────────────────────────────┘
                                     │ SSE token stream
                                     │
             ┌───────────────────────▼───────────────────────────────────┐
             │               PERSISTENCE + ASYNC LAYER                   │
             │                                                           │
             │  PostgreSQL (conversations, messages, partitioned)        │
             │  Kafka (usage events → billing aggregator)                │
             │  S3 (cold conversation archive > 30 days)                 │
             │  OpenTelemetry → Grafana (observability)                  │
             └───────────────────────────────────────────────────────────┘
```

---

## LLD Deep-Dives

### 1. Auth Service — API Key Validation

**Challenge**: Auth must be < 5ms — it's on every single request.

**Design**:
1. Client sends `Authorization: Bearer sk-xxxxxxxxxxxx`
2. Auth service checks Redis cache first (hot path)
3. On cache miss, query PostgreSQL and cache result with 5-minute TTL

```java
@Service
public class ApiKeyAuthService {

    private final ReactiveRedisTemplate<String, ApiKeyContext> redis;
    private final ApiKeyRepository apiKeyRepository;

    /**
     * Validates API key and returns associated user context.
     * Hot path: Redis cache ~1ms.
     * Cold path: DB lookup ~5ms, then cached.
     */
    public Mono<ApiKeyContext> validate(String rawKey) {
        // Never log or store raw keys — hash first
        String keyHash = sha256(rawKey);
        String cacheKey = "apikey:" + keyHash;

        return redis.opsForValue().get(cacheKey)
            .switchIfEmpty(
                apiKeyRepository.findByKeyHash(keyHash)
                    .filter(key -> key.getRevokedAt() == null)
                    .map(key -> ApiKeyContext.from(key))
                    .flatMap(ctx -> redis.opsForValue()
                        .set(cacheKey, ctx, Duration.ofMinutes(5))
                        .thenReturn(ctx))
            )
            .switchIfEmpty(Mono.error(new UnauthorizedException("Invalid API key")));
    }
}
```

---

### 2. Context Service — Managing Conversation State

**Challenge**: LLMs have a context window limit (e.g., 128K tokens for GPT-4).
Conversations grow indefinitely but must fit in the window.

**Strategy**: Sliding window + summarization

```
Strategy 1 (simple): Keep last N messages that fit in context window
  Pros: simple, predictable
  Cons: loses early context

Strategy 2 (smart): Keep all messages, summarize old ones
  "The first 20 messages discussed the user setting up AWS."
  → Summary stored as a synthetic "system" message
  Pros: preserves context meaning
  Cons: requires an LLM call to generate summary (cost + latency)

Strategy 3 (RAG hybrid): Index all past messages, retrieve relevant ones
  Pros: best of both worlds
  Cons: more complex, retrieval latency

For most products: Strategy 1 (simple sliding window). Add 2/3 if users complain.
```

```java
@Service
public class ContextService {

    private static final int MAX_CONTEXT_TOKENS = 100_000;  // Leave 28K for response
    private static final int ESTIMATED_RESPONSE_TOKENS = 28_000;

    /**
     * Builds the final messages array for inference.
     * Fetches from Redis, trims to fit context window.
     *
     * Key insight: trim from the middle — always keep system prompt (start)
     * and the most recent N exchanges (end). This is the "sliding window" strategy.
     */
    public Mono<List<Message>> buildContext(ChatRequest request) {
        String conversationId = request.getConversationId();

        if (conversationId == null) {
            return Mono.just(request.getMessages());
        }

        return fetchHistory(conversationId)
            .map(history -> appendNewMessages(history, request.getMessages()))
            .map(allMessages -> trimToContextWindow(allMessages, MAX_CONTEXT_TOKENS))
            .flatMap(trimmed -> saveUpdatedContext(conversationId, trimmed)
                .thenReturn(trimmed));
    }

    private List<Message> trimToContextWindow(List<Message> messages, int maxTokens) {
        int available = maxTokens - ESTIMATED_RESPONSE_TOKENS;

        // Partition: system messages (keep all) + conversation (trim)
        List<Message> system = messages.stream()
            .filter(m -> "system".equals(m.getRole())).collect(toList());
        List<Message> conversation = messages.stream()
            .filter(m -> !"system".equals(m.getRole())).collect(toList());

        int systemTokens = system.stream().mapToInt(m -> countTokens(m) + 4).sum();
        int budgetForConversation = available - systemTokens;

        // Keep most recent messages that fit
        Deque<Message> kept = new ArrayDeque<>();
        int used = 0;
        for (int i = conversation.size() - 1; i >= 0; i--) {
            int tokens = countTokens(conversation.get(i)) + 4;
            if (used + tokens > budgetForConversation) break;
            kept.addFirst(conversation.get(i));
            used += tokens;
        }

        List<Message> result = new ArrayList<>(system);
        result.addAll(kept);
        return result;
    }
}
```

---

### 3. Inference Orchestrator — Routing and Resilience

**Challenge**: GPU workers crash, get OOM-killed, or become slow.
Route requests without any downtime.

```java
@Service
public class InferenceOrchestrator {

    private final Map<String, List<CircuitBreaker<InferenceWorker>>> modelWorkers;
    private final WorkerHealthMonitor healthMonitor;

    /**
     * Routes request to available worker with circuit breaker protection.
     *
     * Routing strategy:
     * 1. Select model tier (based on request.model)
     * 2. Filter to healthy workers (circuit breaker open = unhealthy)
     * 3. Select least-loaded worker (queue depth aware)
     * 4. If all workers overloaded → queue the request with timeout
     */
    public Flux<String> stream(String model, List<Message> context) {
        String modelTier = resolveModelTier(model);
        InferenceWorker worker = selectWorker(modelTier);

        return worker.streamTokens(context)
            .timeout(Duration.ofSeconds(30))
            .retryWhen(Retry.backoff(2, Duration.ofMillis(500))
                .filter(InferenceOrchestrator::isRetryable))
            .onErrorResume(e -> {
                // Fallback to smaller/faster model if primary fails
                if (model.contains("gpt-4")) {
                    log.warn("GPT-4 failed, falling back to GPT-3.5", e);
                    return stream("gpt-3.5-turbo", context);
                }
                return Flux.error(new InferenceException("All models unavailable", e));
            });
    }

    private InferenceWorker selectWorker(String modelTier) {
        return modelWorkers.getOrDefault(modelTier, List.of()).stream()
            .filter(w -> w.getCircuitBreaker().getState() != State.OPEN)
            .min(Comparator.comparingInt(w -> w.getWorker().getQueueDepth()))
            .map(CircuitBreaker::getWorker)
            .orElseThrow(() -> new NoAvailableWorkerException(modelTier));
    }
}
```

---

### 4. Streaming Response — SSE Implementation

**Challenge**: SSE keeps HTTP connection open for potentially minutes (long responses).
Must not exhaust thread pool or connection pool.

**Solution**: Reactive programming (Project Reactor / WebFlux). Async, non-blocking.

```java
// SSE requires: no buffering, push each token as soon as available
// WebFlux's Flux<ServerSentEvent> handles this natively

@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<ServerSentEvent<String>> streamCompletion(@RequestBody ChatRequest req) {
    return orchestrator.stream(req.getModel(), context)
        .map(token -> ServerSentEvent.<String>builder()
            .id(UUID.randomUUID().toString())
            .event("token")
            .data(token)
            .build())
        .concatWith(Flux.just(
            ServerSentEvent.<String>builder()
                .event("done")
                .data("[DONE]")
                .build()
        ))
        .doOnError(e -> log.error("Stream error for request: {}", req.getId(), e));
}
```

---

## Key Tradeoffs

| Decision                    | Choice                        | Tradeoff                                            |
|-----------------------------|-------------------------------|-----------------------------------------------------|
| Stateless request routing   | Any request → any GPU worker  | No KV cache reuse per session → slightly slower 2nd turn |
| Redis for context (not DB)  | TTL-based, in-memory          | Context lost if Redis restarts → fallback to DB needed |
| SSE over WebSocket          | HTTP streaming                | Can't receive messages mid-stream; fine for chat     |
| Async billing via Kafka     | Eventual consistency          | Billing slightly delayed; users can temporarily exceed limits |
| vLLM continuous batching    | Dynamic batching              | Higher GPU utilization but more complex worker management |

---

## Failure Scenarios and Solutions

```
1. GPU Worker OOM-killed
   → Circuit breaker detects (3 failures in 10s) → marks worker unavailable
   → Kubernetes restarts pod → health check passes → re-added to pool
   → Requests during outage: queued (short burst) or rejected with 503 + Retry-After

2. Redis Context Cache Down
   → Context service falls back to stateless mode (no history)
   → User sees a "conversation context was reset" message
   → Full DB fetch as last resort (slower but correct)

3. Token Limit Exceeded
   → Context trimmer always enforces hard limit BEFORE sending to inference
   → Never reaches the model with oversized context
   → Return 400 with clear message: "Conversation too long, start a new one"

4. Thundering Herd on New Model Release
   → Rate limiter kicks in per API key
   → Request queue with max depth of 1000 (configurable)
   → Requests beyond queue depth → immediate 429 with Retry-After header
```

---

## Observability: What to Monitor

```
Business Metrics (user-facing):
  ├── Time to First Token (TTFT) — p50, p95, p99
  ├── Tokens per second (streaming speed)
  ├── Request success rate
  └── API key error rate (catch bad clients early)

Infrastructure Metrics (operational):
  ├── GPU utilization per worker
  ├── KV cache hit rate
  ├── Queue depth per model tier
  ├── Circuit breaker state per worker
  └── Redis memory usage

Cost Metrics (business):
  ├── Tokens processed per hour (feeds billing)
  ├── Cost per 1000 tokens (GPU time)
  └── Cache hit rate (cached prompts = free)
```

---

**Next**: [09-rag-system.md] — Design a production RAG (Retrieval-Augmented Generation) system.
