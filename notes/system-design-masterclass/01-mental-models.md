# Phase 1 — Mental Models: How Great Architects Think

> The difference between a senior engineer and an architect is not knowledge of tools.
> It is the ability to think in systems before touching a keyboard.

---

## The Core Insight

When you get a system design question — whether in an interview or in real life — your
first instinct should NOT be:
- "I'll use Kafka here"
- "We should shard the database"
- "Let's add Redis"

Those are *answers*. You haven't asked the *questions* yet.

**The architect's first move: understand the problem's shape.**

---

## Mental Model 1 — The 6 Lenses

Every system can be understood through 6 lenses. Train yourself to ask all 6
before drawing a single box.

```
┌─────────────────────────────────────────────────────────────────┐
│                        THE 6 LENSES                             │
│                                                                 │
│  1. TRAFFIC      — How much? What shape? Read-heavy or write?  │
│  2. DATA         — How much? How accessed? How consistent?     │
│  3. LATENCY      — Real-time? Near-real-time? Batch?           │
│  4. FAILURE      — What breaks? What must never fail?          │
│  5. SCALE        — Where does growth come from?               │
│  6. BOUNDARIES   — What is in scope? What is out?             │
└─────────────────────────────────────────────────────────────────┘
```

### Example: "Design a URL shortener"

| Lens      | Question                              | Answer shapes design                            |
|-----------|---------------------------------------|-------------------------------------------------|
| Traffic   | 100 reads per write? 1000?            | Heavy read → cache aggressively                 |
| Data      | How long do URLs live? Analytics too? | TTL in cache, separate analytics pipeline       |
| Latency   | Redirect must be < 10ms?              | In-memory lookup, not DB hit on redirect        |
| Failure   | What if cache is down?                | DB fallback, circuit breaker                    |
| Scale     | Global traffic? Regional?             | CDN or edge function for redirect               |
| Boundaries| Do we need analytics? Custom domains? | Scopes the LLD complexity                       |

---

## Mental Model 2 — The Data Flow Lens

Almost every system is fundamentally a data transformation pipeline.
Ask: *where does data enter, how does it transform, where does it land, and who reads it?*

```
WRITE PATH                              READ PATH

  Client                                  Client
    │                                       │
    ▼                                       ▼
  [Validate + Enrich]                  [Query / Project]
    │                                       │
    ▼                                       ▼
  [Persist / Emit Event]            [Cache / Materialized View]
    │                                       │
    ▼                                       ▼
  [Async Process]                     [Source of Truth]
```

**Key insight**: CQRS and Event Sourcing (which you already know) are just a formalization
of separating the write path from the read path. Apply this thinking everywhere.

### Applying to AI systems

In an AI system, the same lens applies:

```
WRITE PATH                              READ PATH (Inference)

  Raw Data / User Prompt                  User Query
    │                                       │
    ▼                                       ▼
  [Chunk / Embed / Index]             [Embed Query]
    │                                       │
    ▼                                       ▼
  [Vector Store / Feature Store]      [Vector Search]
    │                                       │
    ▼                                       ▼
  [Model Training / Fine-tune]        [LLM Generation]
```

The pattern is the same. The tools are different.

---

## Mental Model 3 — CAP and the Consistency Spectrum

You know CAP. But most architects misapply it. Here's the nuanced version.

**CAP says**: Under a network partition, choose Consistency OR Availability.

**But in practice**: Most systems need different consistency guarantees for different data.

```
STRONG CONSISTENCY ←————————————————————→ EVENTUAL CONSISTENCY
      │                                              │
  Bank balance                              Social media likes
  Inventory count                           News feed order
  Seat booking                              User profile views
  Payment state                             Product view count
```

**The architect's move**: Identify the *consistency boundary* per entity.
- Use strong consistency (RDBMS, distributed transactions) for money and reservations
- Use eventual consistency (Kafka + projections, NoSQL) for everything else

**Your existing system already does this**: Your court case system uses strong consistency
for event sourcing (the event store is the truth), but eventual consistency for the viewstores.

---

## Mental Model 4 — NFR-First Thinking

Non-functional requirements (NFRs) should drive architectural decisions,
not the other way around.

**Wrong approach**: "I'll use Kafka because it's scalable"
**Right approach**: "The NFR is 100k events/sec with < 50ms processing. Let me evaluate if Kafka fits."

### The NFR Hierarchy

```
Tier 1 (Never Negotiable)
  ├── Availability target (99.9%? 99.99%?)
  ├── Data durability (no data loss? at-least-once?)
  └── Security / Compliance requirements

Tier 2 (Performance Envelope)
  ├── p50 / p95 / p99 latency targets
  ├── Throughput (requests/sec, events/sec)
  └── Data freshness (real-time, near-real-time, daily)

Tier 3 (Operational)
  ├── Deployment complexity (team capability)
  ├── Cost constraints
  └── Observability requirements
```

**Pattern**: Every architectural decision should trace back to a Tier 1 or Tier 2 NFR.
If you can't explain *which NFR* a component serves, challenge whether you need it.

---

## Mental Model 5 — The Failure Taxonomy

Architects think about failure before success. Ask these questions about every component:

```
1. What happens if this component is SLOW?       → Timeouts, circuit breakers
2. What happens if this component is DOWN?       → Fallbacks, redundancy
3. What happens if this component gives WRONG data? → Validation, idempotency
4. What happens if this component is FLOODED?    → Rate limiting, backpressure
5. What happens during DEPLOYMENT of this?      → Blue/green, rolling deploy
```

**Real pattern from your notes**: Your DLQ + replay pattern for ActiveMQ messages
is exactly Mental Model 5 applied to message processing:
- SLOW → retry with backoff
- DOWN → DLQ holds the message
- WRONG → correlation ID + inspection
- FLOODED → queue acts as buffer (backpressure)

---

## Mental Model 6 — Scale Dimensions

There are 3 fundamentally different kinds of scale. Each requires different solutions.

```
┌─────────────────┬───────────────────────┬──────────────────────────────┐
│ Scale Type      │ The Bottleneck        │ The Solution                 │
├─────────────────┼───────────────────────┼──────────────────────────────┤
│ Read Scale      │ Database read IOPS    │ Read replicas, caching,      │
│                 │ Network bandwidth     │ CDN, materialized views      │
├─────────────────┼───────────────────────┼──────────────────────────────┤
│ Write Scale     │ Database write IOPS   │ Sharding, CQRS write model,  │
│                 │ Event throughput      │ Kafka partitions, async      │
├─────────────────┼───────────────────────┼──────────────────────────────┤
│ Compute Scale   │ CPU / Memory          │ Horizontal pod autoscaling,  │
│                 │ (e.g., ML inference)  │ GPU clusters, batching       │
└─────────────────┴───────────────────────┴──────────────────────────────┘
```

**AI-era addition — Model Inference Scale**:
LLM inference is a new kind of compute scale problem.
- It's memory-bound (model weights sit in GPU VRAM)
- It's latency-sensitive (streaming tokens, perceived speed matters)
- It's stateless per request but stateful per conversation
- Solutions: batching requests, KV cache reuse, quantization, speculative decoding

---

## How to Apply the 6 Lenses in Practice

### The 3-Step Mental Warm-Up (use this before every design)

**Step 1 — Clarify the problem shape** (2 minutes)
Ask yourself or the interviewer:
- What are the users doing? (write, read, search, stream?)
- What are the scale numbers? (users, data, requests/sec)
- What is the most critical NFR? (availability? latency? consistency?)

**Step 2 — Identify the hardest sub-problem** (2 minutes)
Every system has one part that is fundamentally hard.
- URL shortener → fast global redirect with analytics
- Chat app → real-time delivery + message ordering
- AI chat → low-latency streaming inference + conversation history

Design the hard part first. The rest follows.

**Step 3 — Draw the data flow first** (5 minutes)
Before you place any specific technology:
1. Draw boxes for: Client → Entry Point → Core Processing → Storage → Read Path
2. Label each arrow with the data type and latency requirement
3. Then fill in the technology choices

---

## Practice Exercise

Apply the 6 lenses to this system: *"Design a real-time AI writing assistant (like GitHub Copilot)"*

| Lens      | Your Answer (fill this in)                              |
|-----------|---------------------------------------------------------|
| Traffic   |                                                         |
| Data      |                                                         |
| Latency   |                                                         |
| Failure   |                                                         |
| Scale     |                                                         |
| Boundaries|                                                         |

**Hint**: The hardest sub-problem is latency — streaming token generation must feel
instantaneous. That single NFR will force several architectural decisions.

---

## Summary: The Architect's Mental Stack

```
When given any system design problem:

1. Apply 6 lenses → understand the shape
2. Find the hard sub-problem → design that first
3. Draw data flow → write path + read path
4. Apply NFR-first thinking → each component earns its place
5. Apply failure taxonomy → every component has a failure mode
6. Pick the right scale dimension → don't over-engineer
```

**Next**: [02-hld-framework.md] — How to produce a world-class HLD in 45 minutes.
