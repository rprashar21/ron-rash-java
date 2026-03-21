# Phase 1 — LLD Framework: From Architecture to Code Design

> LLD = Low Level Design. It answers: *how does each component work internally?*
> This is where classes, APIs, schemas, and algorithms are defined.

---

## HLD vs LLD: The Bridge

```
HLD Says:                                  LLD Answers:
──────────────────────────────────────     ─────────────────────────────────────
"We have an inference cluster"          →  How does the request router work?
"We cache conversation history"         →  What is the Redis data structure?
"We emit events to Kafka"               →  What is the event schema? Partitioning key?
"We have a Context Service"             →  What is the class design? What does it return?
"We store conversations in Postgres"    →  What are the tables, indexes, constraints?
```

The HLD defines *what*. The LLD defines *how*.

---

## The LLD Mental Model: 5 Design Surfaces

When doing LLD, work through these 5 surfaces in order:

```
1. API DESIGN         — What does the interface look like? (REST, gRPC, events)
2. DATA MODEL         — What are the entities? How are they stored?
3. CLASS / MODULE     — What are the key abstractions? What are responsibilities?
4. ALGORITHMS         — How does the hard part work? (search, ranking, routing)
5. EDGE CASES         — Concurrency, failure, idempotency, pagination
```

---

## Surface 1 — API Design

Good API design is LLD's most visible output. Apply these principles:

### REST API Design Rules

```
Rule 1: Resources are nouns, never verbs
  BAD:  POST /createUser
  GOOD: POST /users

Rule 2: Use HTTP methods correctly
  GET    → Idempotent, safe (never changes state)
  POST   → Create (not idempotent)
  PUT    → Replace entire resource (idempotent)
  PATCH  → Partial update (can be non-idempotent — be explicit)
  DELETE → Remove (idempotent)

Rule 3: Versioning from day 1
  /api/v1/users    ← version in path (most common)
  /api/v2/users    ← breaking change gets new version

Rule 4: Consistent error format
  {
    "error": {
      "code": "RATE_LIMIT_EXCEEDED",
      "message": "API key exceeded 100 requests/minute",
      "retryAfter": 30
    }
  }

Rule 5: Pagination for lists
  GET /conversations?page=0&size=20&sort=updatedAt,desc
  Response:
  {
    "data": [...],
    "meta": {
      "page": 0,
      "size": 20,
      "total": 1543,
      "hasNext": true
    }
  }
```

### AI Chat API — LLD API Contract

```
POST /v1/chat/completions

Request:
{
  "model": "gpt-4",
  "messages": [
    { "role": "system", "content": "You are a helpful assistant." },
    { "role": "user",   "content": "Explain CQRS in one sentence." }
  ],
  "stream": true,
  "max_tokens": 500,
  "temperature": 0.7
}

Headers:
  Authorization: Bearer <api-key>
  Idempotency-Key: <uuid>          ← your idempotency pattern!
  Content-Type: application/json

Response (streaming, SSE):
  data: {"id":"chatcmpl-123","delta":{"content":"CQRS "},"finish_reason":null}
  data: {"id":"chatcmpl-123","delta":{"content":"separates "},"finish_reason":null}
  data: {"id":"chatcmpl-123","delta":{"content":"reads"},"finish_reason":null}
  data: {"id":"chatcmpl-123","delta":{},"finish_reason":"stop"}
  data: [DONE]

Response (non-streaming):
{
  "id": "chatcmpl-123",
  "model": "gpt-4",
  "choices": [{
    "message": { "role": "assistant", "content": "CQRS separates..." },
    "finish_reason": "stop",
    "index": 0
  }],
  "usage": {
    "prompt_tokens": 45,
    "completion_tokens": 12,
    "total_tokens": 57
  }
}
```

---

## Surface 2 — Data Model

### Schema Design Principles

```
Rule 1: Design for your access patterns first, not normalization
  Bad: normalize everything → then realize you need 5 JOINs for every request
  Good: ask "how will this be queried?" → design schema around that

Rule 2: Every table needs:
  - A surrogate primary key (UUID or BIGSERIAL)
  - created_at + updated_at timestamps
  - Soft delete (deleted_at) if records should be auditable
  - version column if optimistic locking is needed

Rule 3: Index for queries, not for columns
  Don't add indexes on every column.
  Add indexes on: WHERE clause columns, JOIN columns, ORDER BY columns, high-cardinality filters

Rule 4: Separate hot from cold data
  Hot: recent conversations → in PostgreSQL with partition by month
  Warm: 30-90 days → partitioned, maybe compressed
  Cold: archive → S3 / blob storage

Rule 5: Consider read vs write amplification
  More indexes = faster reads, slower writes
  Denormalization = faster reads, data consistency risk
```

### AI Chat API — Data Model

```sql
-- API Keys table
CREATE TABLE api_keys (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id     UUID NOT NULL,
    key_hash    VARCHAR(64) NOT NULL UNIQUE,  -- hashed, never store plaintext
    name        VARCHAR(100),
    rate_limit  INT NOT NULL DEFAULT 100,     -- requests per minute
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    revoked_at  TIMESTAMPTZ,
    INDEX idx_key_hash (key_hash)
);

-- Conversations table
CREATE TABLE conversations (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    api_key_id      UUID NOT NULL REFERENCES api_keys(id),
    model           VARCHAR(50) NOT NULL,
    system_prompt   TEXT,
    token_count     INT NOT NULL DEFAULT 0,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    INDEX idx_api_key_updated (api_key_id, updated_at DESC)
) PARTITION BY RANGE (created_at);   -- monthly partitions (your pattern!)

-- Messages table
CREATE TABLE messages (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id UUID NOT NULL REFERENCES conversations(id),
    role            VARCHAR(20) NOT NULL,   -- 'user' | 'assistant' | 'system'
    content         TEXT NOT NULL,
    token_count     INT NOT NULL,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    INDEX idx_conv_created (conversation_id, created_at ASC)
);

-- Usage events table (append-only, for billing)
CREATE TABLE usage_events (
    id                  BIGSERIAL PRIMARY KEY,
    api_key_id          UUID NOT NULL,
    conversation_id     UUID,
    model               VARCHAR(50) NOT NULL,
    prompt_tokens       INT NOT NULL,
    completion_tokens   INT NOT NULL,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW()
) PARTITION BY RANGE (created_at);   -- monthly partitions
```

### Redis Data Structures for Context Service

```
# Active conversation context (hot path)
Key:    conv:{conversation_id}:context
Type:   String (serialized JSON)
Value:  {
          "messages": [...last N messages...],
          "total_tokens": 1234,
          "model": "gpt-4"
        }
TTL:    4 hours (auto-expire idle conversations)

# Rate limiting (sliding window counter)
Key:    ratelimit:{api_key_id}:{minute_bucket}
Type:   String (integer counter)
Value:  current request count in this minute bucket
TTL:    120 seconds

# Idempotency keys
Key:    idempotency:{idempotency_key}
Type:   String (serialized response)
TTL:    24 hours
```

---

## Surface 3 — Class / Module Design

Apply **Single Responsibility** and keep dependencies explicit.

### AI Chat API — Key Classes (Java/Spring)

```java
// ─────────────────────────────────────────────────────────────────
// 1. ChatController — HTTP entry point only, no business logic
// ─────────────────────────────────────────────────────────────────
@RestController
@RequestMapping("/v1/chat")
public class ChatController {

    private final ChatOrchestrator orchestrator;
    private final IdempotencyService idempotency;

    @PostMapping(value = "/completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> complete(
            @RequestBody @Valid ChatRequest request,
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        ApiKey apiKey = authHeader; // simplified
        return orchestrator.stream(apiKey, request, idempotencyKey);
    }
}

// ─────────────────────────────────────────────────────────────────
// 2. ChatOrchestrator — coordinates the pipeline, no I/O logic
// ─────────────────────────────────────────────────────────────────
@Service
public class ChatOrchestrator {

    private final ContextService contextService;
    private final InferenceRouter inferenceRouter;
    private final UsageEventPublisher usagePublisher;

    public Flux<ServerSentEvent<String>> stream(
            ApiKey apiKey, ChatRequest request, String idempotencyKey) {

        // 1. Build the full prompt with conversation context
        return contextService.buildPrompt(request)
            // 2. Route to appropriate inference worker
            .flatMapMany(prompt -> inferenceRouter.stream(request.getModel(), prompt))
            // 3. Accumulate for usage tracking
            .doOnComplete(() -> usagePublisher.publish(apiKey, request));
    }
}

// ─────────────────────────────────────────────────────────────────
// 3. ContextService — manages conversation state in Redis
// ─────────────────────────────────────────────────────────────────
@Service
public class ContextService {

    private final ReactiveRedisTemplate<String, String> redis;
    private final int maxContextTokens;

    public Mono<List<Message>> buildPrompt(ChatRequest request) {
        if (request.getConversationId() == null) {
            return Mono.just(request.getMessages()); // New conversation
        }

        String key = "conv:" + request.getConversationId() + ":context";
        return redis.opsForValue().get(key)
            .map(json -> merge(deserialize(json), request.getMessages()))
            .map(messages -> trimToTokenLimit(messages, maxContextTokens))
            .defaultIfEmpty(request.getMessages()); // cache miss → use request only
    }
}

// ─────────────────────────────────────────────────────────────────
// 4. InferenceRouter — load balances across GPU workers
// ─────────────────────────────────────────────────────────────────
@Service
public class InferenceRouter {

    private final List<InferenceWorkerClient> workers;
    private final AtomicInteger roundRobinIndex = new AtomicInteger(0);

    public Flux<String> stream(String model, List<Message> messages) {
        InferenceWorkerClient worker = selectWorker();
        return worker.streamCompletion(model, messages)
            .retryWhen(Retry.backoff(3, Duration.ofMillis(200))
                .filter(e -> e instanceof InferenceUnavailableException));
    }

    private InferenceWorkerClient selectWorker() {
        // Round-robin over healthy workers (circuit breaker handles unhealthy)
        List<InferenceWorkerClient> healthy = workers.stream()
            .filter(InferenceWorkerClient::isHealthy)
            .collect(toList());
        int idx = roundRobinIndex.getAndIncrement() % healthy.size();
        return healthy.get(idx);
    }
}
```

---

## Surface 4 — Algorithms

For the AI Chat API, the non-obvious algorithms are:

### Token Counting and Context Window Management

```java
// Naive: count words × 1.3 (rough estimate)
// Better: use a tokenizer library

public class TokenTrimmer {

    private static final int OVERHEAD_PER_MESSAGE = 4; // ChatML format overhead

    /**
     * Trims message history to fit within the model's context window.
     * Strategy: keep system prompt + most recent messages.
     * Time complexity: O(n) where n = message count
     */
    public List<Message> trimToLimit(List<Message> messages, int maxTokens) {
        // Always keep system prompt (index 0 if present)
        Message systemPrompt = messages.stream()
            .filter(m -> "system".equals(m.getRole()))
            .findFirst().orElse(null);

        int reservedTokens = (systemPrompt != null)
            ? countTokens(systemPrompt) + OVERHEAD_PER_MESSAGE : 0;

        int availableForHistory = maxTokens - reservedTokens - 1000; // leave room for response

        // Iterate from most recent, add until limit
        List<Message> result = new ArrayList<>();
        int usedTokens = 0;

        List<Message> history = messages.stream()
            .filter(m -> !"system".equals(m.getRole()))
            .collect(Collectors.toList());
        Collections.reverse(history);

        for (Message msg : history) {
            int msgTokens = countTokens(msg) + OVERHEAD_PER_MESSAGE;
            if (usedTokens + msgTokens > availableForHistory) break;
            result.add(0, msg); // prepend to maintain order
            usedTokens += msgTokens;
        }

        if (systemPrompt != null) result.add(0, systemPrompt);
        return result;
    }
}
```

### Rate Limiting with Sliding Window

```java
// Sliding window rate limiting in Redis
// More accurate than fixed window, prevents boundary bursts

@Service
public class SlidingWindowRateLimiter {

    private final ReactiveRedisTemplate<String, String> redis;
    private final int windowSeconds = 60;

    /**
     * Returns true if request is allowed.
     * Uses Redis sorted set: score = timestamp, member = request_id
     */
    public Mono<Boolean> isAllowed(String apiKeyId, int limitPerMinute) {
        long now = System.currentTimeMillis();
        long windowStart = now - (windowSeconds * 1000L);
        String key = "ratelimit:" + apiKeyId;

        return redis.execute(connection -> {
            // 1. Remove entries older than window
            // 2. Count current entries
            // 3. Add current request if under limit
            // 4. Set TTL
            // All in one Lua script for atomicity
            return connection.scriptingCommands().eval(
                SLIDING_WINDOW_SCRIPT,
                ReturnType.INTEGER,
                List.of(key.getBytes()),
                String.valueOf(now).getBytes(),
                String.valueOf(windowStart).getBytes(),
                UUID.randomUUID().toString().getBytes(),
                String.valueOf(limitPerMinute).getBytes(),
                String.valueOf(windowSeconds + 10).getBytes()
            );
        }).map(result -> (Long) result == 1);
    }

    // Lua script (atomic operation in Redis)
    private static final String SLIDING_WINDOW_SCRIPT = """
        local key = KEYS[1]
        local now = tonumber(ARGV[1])
        local window_start = tonumber(ARGV[2])
        local req_id = ARGV[3]
        local limit = tonumber(ARGV[4])
        local ttl = tonumber(ARGV[5])

        redis.call('ZREMRANGEBYSCORE', key, 0, window_start)
        local count = redis.call('ZCARD', key)

        if count < limit then
            redis.call('ZADD', key, now, req_id)
            redis.call('EXPIRE', key, ttl)
            return 1
        end
        return 0
        """;
}
```

---

## Surface 5 — Edge Cases

These must be in every LLD review:

### Concurrency
- **Problem**: Two requests for the same conversation arrive simultaneously
- **Solution**: Optimistic locking with version in conversation row; or Redis SETNX per conversation

### Idempotency
- **Problem**: Network retry sends same request twice → two responses charged
- **Solution**: Idempotency key stored in Redis with 24h TTL (your existing pattern)

### Graceful Degradation
```
GPU cluster unhealthy → serve from degraded model (smaller, faster)
Redis context unavailable → proceed with stateless (no conversation history)
DB write fails → event logged to Kafka DLQ → retry async
```

### Pagination Cursor vs Offset
```
Offset pagination:
  GET /messages?offset=100&limit=20
  Problem: if records added while paginating, you skip or duplicate rows

Cursor pagination (preferred for large or live data):
  GET /messages?cursor=<last_seen_id>&limit=20
  Stable: always gives next 20 after the cursor regardless of inserts
```

---

## LLD Checklist

Before presenting your LLD, verify:

- [ ] APIs are versioned and have consistent error shapes
- [ ] Schema has access-pattern-optimized indexes
- [ ] Classes have single responsibilities
- [ ] Idempotency handled for all state-changing operations
- [ ] Token/character/size limits explicitly enforced
- [ ] Concurrency races identified and resolved
- [ ] Failure fallbacks at every I/O boundary
- [ ] Pagination uses cursor for live/large data

---

**Next**: [04-scalability-patterns.md] — Master the scaling toolkit.
