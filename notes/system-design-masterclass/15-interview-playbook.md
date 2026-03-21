# Phase 4 — Interview Playbook: 45-Minute System Design

> This is the battle plan. Use it every time until it becomes instinct.

---

## The 45-Minute Clock

```
[0-5]   Clarify + Scope
[5-15]  Back-of-envelope + Define Constraints
[15-30] HLD: Core Architecture
[30-40] Deep Dive: The Hard Part
[40-45] Discuss Tradeoffs + Scale + Improvements
```

**Rule**: Move through phases even if incomplete. A shallow-but-complete journey beats
a deep-but-stuck single component.

---

## Phase 1: Clarify (0-5 min)

Ask exactly these 5 questions. Do not skip them. Do not answer for yourself.

```
1. "Who are the users? What exactly are they doing?"
   → Defines the write path and read path

2. "What is the expected scale? DAU, peak RPS, data volume?"
   → Tells you if you need caching, sharding, partitioning

3. "What is the most important NFR — latency, availability, or consistency?"
   → Drives your biggest architectural decisions

4. "What is explicitly out of scope for today?"
   → Prevents you from designing something nobody asked for

5. "Are there any known constraints? Cloud provider, existing tech, regulations?"
   → Respects real-world context
```

**Pro tip**: While asking, sketch a simple box on your mental whiteboard: User → ? → DB.
The clarification answers fill in the ?.

---

## Phase 2: Estimate the Numbers (5-15 min)

Do math out loud. Interviewers love this. It shows engineering thinking.

### The Standard Estimates

```
Given: N users or N requests/day

Step 1: Requests/sec
  avg_rps = N / 86,400 (seconds in a day)
  peak_rps = avg_rps × 10 (typical peak multiplier)

Step 2: Storage/day
  storage_per_day = peak_rps × payload_size × 86,400
  annual_storage = storage_per_day × 365

Step 3: Bandwidth
  bandwidth = peak_rps × payload_size

Step 4: Compute
  For API: 1 server handles ~1,000 RPS (rough)
           servers_needed = peak_rps / 1000
  For DB:  PostgreSQL: ~10,000 QPS well-indexed
           read_replicas = if read QPS > 10k
  For GPU (AI): depends on model size (see 08-ai-inference-platform.md)
```

**Say out loud**: "Let me do some quick back-of-envelope to make sure my design is in the right ballpark..."

---

## Phase 3: HLD Core Architecture (15-30 min)

Draw this structure. Fill in the specifics for the problem.

### The Universal HLD Template

```
                    [CLIENT]
                       │
                    [EDGE]
                    CDN / DNS / DDoS
                       │
                  [GATEWAY]
                  Auth / Rate Limit / Route
                       │
          ┌────────────┴────────────┐
          │                        │
    [WRITE PATH]              [READ PATH]
    Command API               Query API
    Validation                Cache Layer
    Event Store               Search/Index
          │                        │
          └────────────┬───────────┘
                  [ASYNC TIER]
                  Queue / Stream
                  Worker Pool
                       │
               [DATA STORES]
               Primary DB / Cache / Search
```

### Fill in the technology using the NFR

| NFR                        | Technology Choice                               |
|----------------------------|-------------------------------------------------|
| Real-time, bidirectional   | WebSocket                                       |
| Streaming (server→client)  | SSE (Server-Sent Events)                        |
| High-throughput events     | Kafka                                           |
| Point-to-point commands    | JMS / ActiveMQ                                  |
| Strong consistency + ACID  | PostgreSQL / MySQL                              |
| Massive write scale        | Cassandra / DynamoDB                            |
| Full-text search           | Elasticsearch                                   |
| Semantic search (AI)       | pgvector / Pinecone / Qdrant                    |
| Session / hot cache        | Redis                                           |
| Object storage             | S3 / Azure Blob                                 |
| Async compute              | Kubernetes Job / Lambda                         |

---

## Phase 4: Deep Dive (30-40 min)

Pick the hardest component in your HLD and go deep. The interviewer often directs you.

### How to pick the hard part

Ask yourself: "What's the component that would be career-defining if you got wrong?"
- Chat app → message ordering and delivery guarantees
- Payment system → idempotency and double-charge prevention
- Search engine → relevance ranking algorithm
- AI system → inference latency and streaming

### Deep-dive structure (5 minutes per component)

```
1. State the problem clearly
   "The challenge here is X because Y"

2. State options considered
   "I considered [Option A] and [Option B]"

3. Explain your choice + why
   "I chose [Option B] because [NFR match]"

4. Show the data flow or algorithm
   Draw it if on whiteboard, describe it verbally

5. State the tradeoff
   "The cost of this is [Z], mitigated by [W]"
```

---

## Phase 5: Tradeoffs + Scale (40-45 min)

This phase separates senior engineers from principal engineers.

### The 5 Things to Always Mention

```
1. What breaks first at 10× scale, and how would you fix it?
   → Shows you can think ahead

2. What would you do differently if you had more time?
   → Shows self-awareness and priorities

3. What is the failure mode of your most critical component?
   → Shows reliability thinking

4. How would you monitor this system?
   → Metrics to track (latency p99, error rate, queue depth, etc.)

5. One thing you deliberately left simple that could be improved
   → Shows judgment (not everything needs to be perfect)
```

---

## Cheat Sheet: Common System Design Patterns

### When to use each database

```
PostgreSQL / MySQL (RDBMS):
  → Transactions required
  → Complex queries / joins
  → <100M rows per table (with partitioning can go further)
  → Financial data, user data, structured business data

Redis:
  → Cache layer (hot reads)
  → Session storage
  → Rate limiting
  → Pub/Sub (lightweight event streaming)
  → Leaderboards, counters

Cassandra / DynamoDB:
  → Write-heavy workloads (IoT, logs, events)
  → Known access patterns (no ad-hoc queries)
  → Horizontal scale beyond what RDBMS handles
  → Time-series data

Elasticsearch:
  → Full-text search
  → Log aggregation + search
  → Analytics with flexible ad-hoc queries

Kafka:
  → Event streaming at high throughput
  → Decoupling services with durable messages
  → Event sourcing / log compaction
  → Real-time data pipelines

pgvector / Pinecone:
  → Semantic similarity search
  → RAG systems
  → Recommendation engines using embeddings
```

### When to use each communication pattern

```
REST (HTTP/JSON):
  → CRUD operations
  → External APIs (clients don't share your tech)
  → Cacheable requests (GET)
  → Simple request-response

gRPC:
  → Service-to-service (internal)
  → High-throughput, low-latency
  → Streaming (server-streaming, bi-directional)
  → Strongly typed contracts

WebSocket:
  → Bidirectional real-time (chat, gaming, collaborative editing)

SSE (Server-Sent Events):
  → Server-to-client streaming (AI token streaming, live dashboards)
  → Simpler than WebSocket when only server pushes

Kafka / Message Queue:
  → Async, decoupled communication
  → Fan-out to multiple consumers (events)
  → Point-to-point with retry (commands)
  → Buffer traffic spikes
```

---

## Common Mistakes and How to Avoid Them

```
MISTAKE: Starting with technology
  FIX:    Start with requirements, then flow, then technology

MISTAKE: Designing for today's scale only
  FIX:    Ask "what breaks first at 10× traffic?" in the tradeoffs phase

MISTAKE: Forgetting about failures
  FIX:    For each component, ask "what happens if this is slow/down/wrong?"

MISTAKE: Perfect design (trying to address everything)
  FIX:    State what you're simplifying: "I'm not designing auth in detail, assuming JWT"

MISTAKE: Silent for long periods
  FIX:    Think out loud. "I'm considering X because of Y, but I'm weighing Z..."

MISTAKE: No data flow, just boxes
  FIX:    Every arrow must have a protocol and data type

MISTAKE: Ignoring the read path
  FIX:    Always draw write path AND read path separately
```

---

## The 10 Most Common Interview Questions (with approach hints)

| Question                           | Key Insight                                           |
|------------------------------------|-------------------------------------------------------|
| Design Twitter / X                 | Fan-out problem, celebrity problem, timeline generation |
| Design YouTube / Netflix           | CDN + adaptive bitrate, recommendation engine          |
| Design URL Shortener               | Tiny writes, massive reads, base62 encoding            |
| Design WhatsApp / Chat             | Message ordering, delivery guarantees, WebSocket       |
| Design Uber / Lyft                 | Geo-spatial index, driver location streaming, matching |
| Design Google Search               | Web crawler, inverted index, PageRank                  |
| Design Rate Limiter                | Token bucket vs sliding window, distributed sync       |
| Design Notification System         | Fan-out, push vs pull, digest vs real-time             |
| Design AI Chat API (GPT-like)      | GPU cluster, KV cache, streaming, context management   |
| Design a RAG system                | Indexing pipeline, vector DB, chunking, re-ranking     |

---

## Phrases That Signal Senior Architect Thinking

Use these to sound like you've designed systems under production load:

```
"My concern with that approach at scale is..."
"The failure mode I'd worry about is..."
"I'd A/B test X before committing to Y..."
"At 10× traffic, the bottleneck would be..."
"I'd instrument this with [specific metric] to know when to..."
"This is a tradeoff between [A] and [B]. Given the NFR says [X], I'd choose..."
"I'd start simple here and only add complexity if we measured [problem]..."
"The operational cost of this choice is [Z]. The team would need to..."
"I deliberately left X simple because [reason]. Here's how I'd evolve it..."
```

---

## Your Personal Strengths to Emphasize

Based on your existing experience, lean heavily into these in interviews:

```
1. CQRS + Event Sourcing
   → Not everyone has production experience with this
   → Leads naturally into agent platforms, RAG indexing, AI audit trails

2. ActiveMQ / JMS in production
   → Use it to explain async decoupling, idempotency, DLQ
   → Bridge to Kafka: know when JMS is better (point-to-point commands)

3. AKS + Azure architecture
   → Ingress controller, Application Gateway, HPA, pod autoscaling
   → Shows real cloud-native infrastructure thinking

4. Microservices at scale
   → Service isolation, independent deployment, circuit breakers
   → Directly applicable to AI systems (each model = a service)

5. Circuit breakers + resilience patterns
   → Every AI system needs this (models go down, rate limits hit)
   → Shows you've operated systems that fail in the real world
```

---

## The 5-Minute Practice Drill

Every day, pick one of these and apply the 6 lenses + data flow sketch (5 minutes max):

```
Week 1: Core systems
  Mon: URL shortener       Tue: Notification service    Wed: Rate limiter
  Thu: File upload service Fri: Leaderboard system

Week 2: AI systems
  Mon: AI chat API         Tue: RAG document search     Wed: AI image generator
  Thu: AI code assistant   Fri: Recommendation engine

Week 3: Platform systems
  Mon: API gateway         Tue: Distributed cache        Wed: Message queue
  Thu: Search engine       Fri: Monitoring system
```

---

*End of tutorial series core modules.*

**Remember**: The goal is not to memorize answers.
The goal is to build the mental reflex — the 6 lenses, the data flow, the failure modes —
so that when you sit down to design anything, the thinking flows naturally.

Every great system design skill comes from: **draw → get feedback → improve → repeat.**
