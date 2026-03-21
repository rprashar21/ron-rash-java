# Phase 4 — Tradeoff Framework: How Architects Make Decisions

> "The measure of a great architect is not the decisions they make but the tradeoffs they articulate."
> Every choice closes one door and opens another. Your job is to know which doors matter.

---

## The Tradeoff Trifecta

Every architectural decision lives in a tension between three forces:

```
                         ┌──────────────────────┐
                         │   PERFORMANCE         │
                         │   (speed, throughput) │
                         └───────────┬───────────┘
                                     │
                       ┌─────────────┴─────────────┐
                       │                           │
             ┌─────────▼──────────┐   ┌────────────▼─────────┐
             │   CONSISTENCY      │   │   AVAILABILITY       │
             │   (correctness,    │   │   (uptime, fault     │
             │    accuracy)       │   │    tolerance)        │
             └────────────────────┘   └──────────────────────┘

You can optimize for 2 of 3, but not all 3 simultaneously.
```

**Real examples**:
- **Strong consistency + high availability** → sacrifice performance (distributed transactions are slow)
- **High performance + high availability** → sacrifice strong consistency (eventual consistency)
- **High performance + strong consistency** → sacrifice availability (reject requests if can't guarantee)

---

## The 8 Universal Tradeoffs

These are the tradeoffs every architect must know cold. You will encounter them in every system.

---

### Tradeoff 1: Latency vs Throughput

```
Latency:    Time to process ONE request (fast response for that user)
Throughput: Requests processed per second (overall system capacity)

They conflict because:
  Higher throughput often means batching → each individual request waits in batch
  Lower latency often means processing immediately → less efficient use of resources
```

**Example from your domain**: In your court case system, you could batch event processing
for higher throughput, but individual command handlers would have higher latency.
You chose low latency (immediate JMS processing) because the judicial process requires it.

**The architect's question**: "Is this a latency-sensitive user path or a throughput-sensitive background job?"
- User-facing API: optimize for latency
- Nightly batch jobs: optimize for throughput
- Real-time dashboards: both matter → design the data pipeline first

---

### Tradeoff 2: Consistency vs Availability (CAP)

```
Strong Consistency:
  All nodes see the same data at the same time.
  A read always returns the most recent write.
  Example: bank balance, inventory count, seat booking.
  Cost: slower writes (must sync all nodes), partition intolerance.

Eventual Consistency:
  Nodes will converge to the same data eventually.
  A read might return stale data.
  Example: social media likes, product view counts, activity feeds.
  Cost: complexity in resolving conflicts, stale reads.
```

**How to choose**:
```
Ask: "What's the worst case if a user sees stale data?"

"User sees like count as 100 instead of 101" → eventual consistency fine
"User sees account balance as £500 instead of £200" → MUST be strongly consistent
"User books a seat already taken" → MUST be strongly consistent
"User sees a slightly old version of a document" → eventual consistency fine
```

**The practical rule**: Use eventual consistency everywhere EXCEPT:
- Money / financial state
- Inventory / reservations / seats
- Access control / permissions
- Legal / audit records (your domain!)

---

### Tradeoff 3: Read Performance vs Write Performance

```
Optimizing reads:
  → Add indexes (slows writes)
  → Add materialized views (double writes)
  → Denormalize (write duplication)
  → Add caching (cache invalidation complexity)

Optimizing writes:
  → Remove indexes (slows reads)
  → Append-only log (reads must scan or project)
  → Normalize (more joins on read)
  → Async writes (reads may be stale)
```

**This is the central tension in CQRS** — which you already know deeply.
CQRS is just the formalization of: "my write model is optimized for writes, my read model is optimized for reads, and I pay the cost of keeping them in sync."

**The architect's question**: "What is the read:write ratio?"
- 100:1 (typical web app) → optimize heavily for reads, tolerate slower writes
- 1:100 (IoT sensors) → optimize for writes, batch reads
- 1:1 (trading system) → optimize both, pay more

---

### Tradeoff 4: Scalability vs Simplicity

```
Every scalability pattern adds complexity:
  ├── Horizontal scaling → need load balancer, stateless services, distributed sessions
  ├── Sharding → need routing logic, cross-shard queries become hard
  ├── CQRS → need event bus, projection handlers, DLQ, replay
  ├── Caching → need cache invalidation, TTL strategy, cold start
  └── Microservices → need service discovery, distributed tracing, API gateway

The complexity is real. It has a cost:
  ├── More things can fail
  ├── Harder to debug
  ├── More infrastructure to manage
  └── Longer onboarding for new developers
```

**The architect's rule**: "Don't scale what you haven't measured."
1. Start simple (monolith, single database)
2. Measure and identify the bottleneck
3. Apply the targeted scalability pattern
4. Measure again

**Anti-pattern**: Applying all scalability patterns upfront "because we might need it."
The court system example from your notes is a good one — you use complex CQRS+ES architecture
because the audit requirements *demand* it. The complexity is justified.

---

### Tradeoff 5: Cost vs Performance

```
Performance costs money:
  GPU inference cluster: £10k-£50k/month for serious workloads
  Redis cluster: £1k-£10k/month
  Multi-region deployment: 2-3× single-region cost
  Premium SSD IOPS: 10× standard disk cost

Cost-saving techniques reduce performance:
  Spot/preemptible instances → can be evicted (availability risk)
  Tiered storage → S3 reads are slower than SSD
  Smaller models → lower quality AI output
  Fewer replicas → single points of failure
```

**The architect's question**: "What is the cost of NOT having this performance?"
- E-commerce: 100ms slower → 1% fewer conversions → calculate revenue impact
- AI inference: 500ms more latency → users perceive it as slow → user retention impact
- Background batch job: 2h slower → nobody notices → cost savings justified

---

### Tradeoff 6: Durability vs Performance

```
Durability (data won't be lost) costs performance:
  ├── Synchronous disk flush → slower writes but survives crash
  ├── Replication to multiple nodes → slower writes but no data loss on node failure
  ├── Write-ahead logging (WAL) → overhead but crash recovery
  └── Cross-region replication → high latency but survives AZ outage

Systems weaken durability for performance:
  ├── In-memory databases (Redis without persistence) → fast but data lost on restart
  ├── Kafka with acks=1 → fast but message lost if leader crashes before replication
  ├── Write buffering → fast but buffer lost if process crashes
```

**The architect's question**: "What is the cost of losing this data?"
- Cache data → low cost, Redis without persistence is fine
- Conversation history → medium cost, persist with short delay (Kafka + async DB write)
- Financial transactions → very high cost, require synchronous durability (PostgreSQL fsync=on)

---

### Tradeoff 7: Coupling vs Autonomy

```
Tight coupling (direct service calls):
  + Simple to understand
  + Easy to trace
  - Single point of failure
  - Downstream service latency affects you
  - Services must deploy together

Loose coupling (events/messages):
  + Independent deployments
  + Fault isolation
  + Natural backpressure via queue
  - Eventual consistency
  - Harder to trace (correlation ID required)
  - More infrastructure (message broker)
```

**This is again your CQRS domain.** Commands vs events:
- Commands: tight coupling by design (you want exactly one handler to respond)
- Events: loose coupling by design (you don't care who or how many react)

**The rule**: Use events for cross-domain notifications. Use direct calls for within-domain operations where you need the result.

---

### Tradeoff 8: Vendor-Managed vs Self-Managed

```
Managed services (e.g., Azure Cosmos DB, Confluent Kafka, Pinecone):
  + No operations burden
  + SLA guaranteed by vendor
  + Auto-scaling, backups, patching
  - Vendor lock-in
  - Higher cost at scale
  - Limited customization

Self-managed (e.g., self-hosted Kafka on AKS):
  + Full control
  + Lower cost at scale
  + No lock-in
  - Operations burden (team must know how to run it)
  - You own the SLA
```

**The architect's rule**: "Use managed until you have a reason not to."
Reasons to self-manage:
- Cost exceeds team capacity to run it
- Regulatory requirements (data sovereignty)
- Performance requirements that managed can't meet
- Specific customization needed

---

## How to Communicate Tradeoffs

In interviews and in real architecture reviews, use this structure:

```
"I chose [OPTION A] over [OPTION B] because [NFR / REQUIREMENT].
The cost of this choice is [DOWNSIDE].
I mitigate that by [MITIGATION].
I would reconsider if [TRIGGER]."
```

**Example**:
"I chose eventual consistency for the product view counter over strong consistency
because view counts don't need to be exact — a margin of ±100 is acceptable.
This lets us use Redis INCR with no distributed locking, giving us O(1) writes at any scale.
The cost is that counts may be slightly stale in the UI.
I mitigate that by showing counts as "~1.2k views" rather than exact numbers.
I would reconsider if the product required exact analytics (e.g., billing per view), in which case I'd use an append-only Kafka event stream with batch aggregation."

---

## The Tradeoff Decision Matrix

Use this when evaluating options:

| Option        | Performance | Consistency | Availability | Cost | Complexity |
|---------------|-------------|-------------|--------------|------|------------|
| Option A      | High        | Strong      | Medium       | High | High       |
| Option B      | Medium      | Eventual    | High         | Low  | Low        |
| Option C      | Low         | Strong      | High         | High | Very High  |

Pick based on which columns your NFRs prioritize.

---

**Next**: [15-interview-playbook.md] — The 45-minute system design interview strategy.
