# Phase 1 — HLD Framework: Design Systems in 45 Minutes

> HLD = High Level Design. It answers: *what are the major components and how do they talk?*
> The goal is not a complete diagram. It's a clear communication of tradeoffs and structure.

---

## The 45-Minute HLD Template

This is the template used by principal engineers at FAANG for system design interviews
and real architecture reviews. Adapt it to your context.

```
[0-5 min]   Clarify Requirements + Define Scope
[5-15 min]  Define Scale + NFRs (the numbers)
[15-30 min] HLD: Core Components + Data Flow
[30-40 min] Deep-Dive: The Hard Part
[40-45 min] Tradeoffs + What You'd Do Differently at 10x Scale
```

---

## Step 1 — Clarify Requirements (5 minutes)

Ask these 5 questions. Every answer changes the design.

**1. Who are the users and what are they doing?**
- Write-heavy users (uploading, posting)
- Read-heavy users (viewing, searching)
- Mixed (social media: 1 write : 100 reads)

**2. What are the scale expectations?**
- DAU (Daily Active Users)
- Peak requests per second
- Data volume (GB/day, TB/year)

**3. What is the most critical NFR?**
- Availability target (99.9% = 8.7h downtime/year, 99.99% = 52 min)
- Latency target (< 100ms? < 1s?)
- Data consistency (banking: strong, social: eventual)

**4. What is explicitly OUT of scope?**
- This prevents you from over-designing

**5. Any existing constraints?**
- Cloud provider? Existing tech? Regulatory requirements?

### Example: "Design an AI Chat API (like OpenAI's API)"

| Question         | Answer                                                         |
|------------------|----------------------------------------------------------------|
| Users            | Developers calling the API; end-users of those apps            |
| Scale            | 10M API calls/day, peaks at 100k req/min during viral moments  |
| Critical NFR     | < 500ms first token, < 100ms/token streaming, 99.9% SLA       |
| Out of Scope     | Training pipeline, billing UI, model development               |
| Constraints      | Multi-cloud; support GPT-4 class models (70B+ params)          |

---

## Step 2 — Define the Numbers (10 minutes)

**Always calculate before you design.** The numbers tell you what components you need.

### The Back-of-Envelope Toolkit

```
Memory:
  1 token ≈ 4 bytes
  1 million tokens ≈ 4 MB
  GPT-4 70B model weights ≈ 140 GB (FP16)

Storage:
  1 KB = 1,000 bytes
  1 MB = 1,000 KB
  1 GB = 1,000 MB
  1 TB = 1,000 GB

Throughput:
  Typical SSD: 500 MB/s
  Typical network: 1-10 Gbps
  Redis: 100k ops/sec (single node)
  Kafka: 1M messages/sec (partitioned cluster)
  PostgreSQL: 10k-50k queries/sec (well-tuned)

Time:
  L1 cache: 1 ns
  RAM access: 100 ns
  SSD read: 100 μs
  Network round trip (same DC): 1 ms
  Network round trip (cross-region): 50-150 ms
  HDD seek: 10 ms
```

### Applied Example: AI Chat API Numbers

```
Given: 10M API calls/day

Requests per second (avg):
  10,000,000 / 86,400 = ~116 req/sec

Peak (assume 10x burst):
  1,160 req/sec

Per-request data:
  Avg prompt: 500 tokens = 2 KB
  Avg response: 1000 tokens = 4 KB
  Total: ~6 KB/request

Storage per day:
  10M × 6 KB = 60 GB/day of conversation data

Inference compute:
  1 A100 GPU (80GB VRAM) can serve:
    - 70B model: ~5-10 req/sec (streaming)
    - 7B model: ~50-100 req/sec
  For 1,160 peak req/sec with a 7B model: ~12-23 GPU nodes
```

**The numbers just told you**:
- You need a GPU cluster, not a CPU cluster
- You need a router to distribute across GPU nodes
- 60 GB/day of logs → choose a columnar store for analytics
- Peaks at 10x → autoscaling is critical

---

## Step 3 — Draw the HLD (15 minutes)

### The Standard HLD Structure

Every system follows this skeleton. Fill in the specifics.

```
┌─────────────────────────────────────────────────────────────────┐
│                        CLIENT TIER                              │
│         (Mobile / Web / Third-party API consumers)              │
└───────────────────────────┬─────────────────────────────────────┘
                            │ HTTPS / WebSocket
┌───────────────────────────▼─────────────────────────────────────┐
│                        EDGE TIER                                │
│   CDN  │  DDoS protection  │  DNS  │  API Gateway / APIM       │
└───────────────────────────┬─────────────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────────────┐
│                      GATEWAY TIER                               │
│   Auth  │  Rate Limiting  │  Request Routing  │  Tracing       │
└──────┬──────────────────────────────────────┬───────────────────┘
       │                                      │
┌──────▼──────────┐                 ┌─────────▼──────────────────┐
│  WRITE PATH     │                 │     READ / QUERY PATH      │
│  Command API    │                 │     Query API              │
│  ─────────────  │                 │     ──────────────────     │
│  Validation     │                 │     Materialized Views     │
│  Event Emission │                 │     Cache Layer            │
│  Event Store    │                 │     Search Index           │
└──────┬──────────┘                 └─────────▲──────────────────┘
       │                                      │
┌──────▼──────────────────────────────────────┴──────────────────┐
│                    ASYNC PROCESSING TIER                        │
│   Message Queue  │  Stream Processor  │  Worker Pool           │
└──────┬──────────────────────────────────────┬───────────────────┘
       │                                      │
┌──────▼──────────┐                 ┌─────────▼──────────────────┐
│  PRIMARY STORE  │                 │     SECONDARY STORES       │
│  (Source of     │                 │     Read Replicas          │
│   Truth)        │                 │     Cache (Redis)          │
└─────────────────┘                 └────────────────────────────┘
```

### AI Chat API — HLD Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                    DEVELOPER / END USER                         │
└───────────────────────────┬─────────────────────────────────────┘
                            │ POST /v1/chat/completions
                            │ (streaming SSE or JSON)
┌───────────────────────────▼─────────────────────────────────────┐
│                    API GATEWAY + AUTH                           │
│    Rate Limiting per API key  │  JWT validation  │  TLS         │
└──────────┬──────────────────────────────────────────────────────┘
           │
┌──────────▼──────────────────────────────────────────────────────┐
│                    ROUTING LAYER                                │
│    Model router: selects GPT-4 / GPT-3.5 based on request      │
│    Load balancer: distributes to available inference workers    │
└──────────┬──────────────────────────────────────────────────────┘
           │                               │
┌──────────▼──────────┐       ┌────────────▼────────────────────┐
│  CONTEXT SERVICE    │       │   INFERENCE CLUSTER             │
│  ─────────────────  │       │   ──────────────────────────    │
│  Fetch conversation │       │   GPU Workers (A100/H100)       │
│  history from Redis │       │   vLLM / TensorRT inference     │
│  Trim to token limit│       │   KV Cache per session          │
│  Build final prompt │       │   Token streaming (SSE)         │
└──────────┬──────────┘       └────────────┬────────────────────┘
           │                               │
           └──────────────┬────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────────────┐
│                    PERSISTENCE TIER                             │
│                                                                 │
│  Redis Cluster          │  Conversation DB      │  Kafka       │
│  (session KV cache,     │  (PostgreSQL / Cosmos) │  (audit log, │
│   hot conversations)    │  long-term history    │  billing)    │
└─────────────────────────────────────────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────────────┐
│              OBSERVABILITY + ASYNC PROCESSING                   │
│  Prometheus/Grafana  │  OpenTelemetry  │  Billing Worker       │
│  Token counting      │  Request tracing │  Usage aggregation   │
└─────────────────────────────────────────────────────────────────┘
```

---

## Step 4 — Deep-Dive the Hard Part (10 minutes)

Identify the hardest sub-problem in your HLD and zoom in.

For the AI Chat API, the hardest part is: **low-latency streaming inference at scale**.

### Deep-Dive: Inference Tier

**Problem**: LLM inference is sequential (each token depends on previous ones).
This is fundamentally different from normal request processing.

```
TOKEN GENERATION (sequential):
Input Tokens → [Model]→ Token 1 → [Model] → Token 2 → [Model] → Token N

The model's attention mechanism must process all previous tokens
each step → this is the "KV Cache" problem.
```

**Solution: KV Cache + Continuous Batching**

```
WITHOUT KV Cache:
  Each token generation recomputes attention for ALL previous tokens
  Cost: O(n²) where n = sequence length → very slow for long contexts

WITH KV Cache:
  Cache the key-value matrices from previous tokens
  Each new token only processes the new token's attention
  Cost: O(n) per new token → 10-100x faster

  ┌────────────────────────────────────────────┐
  │  GPU Worker                                │
  │  ─────────────────────────────────────── │
  │  KV Cache (stored in VRAM)                 │
  │  [ K1, V1, K2, V2, ..., Kn, Vn ]          │
  │  New token → append → generate next token │
  └────────────────────────────────────────────┘
```

**Continuous Batching** (how to serve multiple users on one GPU):

```
Traditional batching:
  Wait for N requests → batch them → process → respond
  Problem: some requests finish early, GPU sits idle

Continuous batching (used by vLLM):
  As soon as one request finishes → immediately insert new request
  GPU utilization goes from 30% → 80%+

  Time: ─────────────────────────────────────────────
  GPU:  [Req A][Req A][Req A][Req A][Req B][Req B]...
                          ↑
                    [Req B inserted when Req A finishes]
```

---

## Step 5 — Tradeoffs (5 minutes)

Every decision is a tradeoff. State yours explicitly.

### Tradeoff Table for AI Chat API

| Decision               | Choice Made            | What You Gave Up               | Why Worth It                       |
|------------------------|------------------------|--------------------------------|------------------------------------|
| Streaming via SSE      | SSE (HTTP streaming)   | WebSocket's bidirectional      | Simpler; LLM output is one-way     |
| Redis for context      | In-memory + expiry     | Full persistence               | Speed; conversations are ephemeral |
| Stateless routing      | Route any req to any GPU| Session affinity / KV reuse    | Easier scaling; tolerate GPU failure|
| Async billing via Kafka| Eventual consistency   | Exact real-time billing        | Billing doesn't need to block user |
| PostgreSQL for history | Relational DB          | NoSQL flexibility              | Conversation structure is relational|

---

## The HLD Checklist

Before presenting your HLD, verify:

- [ ] Every major component is named and has a single responsibility
- [ ] Every arrow between components has a protocol (REST, gRPC, Kafka, WebSocket)
- [ ] The write path is separate from the read path
- [ ] Every component has a failure mode addressed
- [ ] The scale numbers justify the complexity
- [ ] You've called out the hardest sub-problem
- [ ] You've listed at least 3 explicit tradeoffs

---

## Common HLD Mistakes (and how to avoid them)

**Mistake 1: Jumping to technology before understanding the problem**
Fix: Spend 5 minutes on requirements. No technology names until you have the data flow.

**Mistake 2: Making every component stateless as a default**
Fix: Some state is inherent (model KV cache, user sessions). Design for it explicitly.

**Mistake 3: Ignoring the operational complexity of your choices**
Fix: Ask "can my team operate this?" A Kafka cluster is not free. Neither is a vector database.

**Mistake 4: Treating the database as an afterthought**
Fix: Database choice and schema design are HLD-level decisions. They cannot be changed cheaply.

**Mistake 5: Not drawing the failure path**
Fix: For each core component, draw the fallback. A circuit breaker, a DLQ, a cache fallback.

---

**Next**: [03-lld-framework.md] — Translating HLD decisions into code-level design.
