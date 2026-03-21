# System Design Masterclass — Senior Architect Track
> Built on your existing CQRS / microservices / Kafka / Azure foundation.
> Focus: mental models → HLD/LLD frameworks → AI-era system design.

---

## Why This Series Exists

Most system design guides teach you *what* (CDN, sharding, Kafka).
This series teaches you *how to think* — so you can design any system you've never seen before,
especially the AI-native systems that dominate 2024–2026 engineering.

---

## Learning Path

```
PHASE 1 — Mental Models (think before you draw)
  01-mental-models.md          ← The 6 lenses. How great architects frame problems.
  02-hld-framework.md          ← How to produce a world-class HLD in 45 minutes.
  03-lld-framework.md          ← Translating HLD decisions into code-level design.

PHASE 2 — Core Building Blocks (master the toolkit)
  04-scalability-patterns.md   ← Scale reads, writes, compute, and state.
  05-reliability-patterns.md   ← Failure modes, resilience, CAP/PACELC.
  06-data-patterns.md          ← Choosing and combining databases correctly.
  07-async-patterns.md         ← Queues, streams, event sourcing, sagas.

PHASE 3 — Real World AI Systems (the frontier)
  08-ai-inference-platform.md  ← Design a ChatGPT-like inference API (HLD+LLD).
  09-rag-system.md             ← Design a production RAG system (HLD+LLD).
  10-ai-agent-platform.md      ← Design an AI Agent orchestration platform.
  11-ml-feature-store.md       ← Design a Feature Store for ML pipelines.
  12-vector-db-deep-dive.md    ← Vector databases: internals and design.

PHASE 4 — Architect Thinking
  13-tradeoff-framework.md     ← How to make and communicate tradeoffs.
  14-nfr-driven-design.md      ← NFRs as first-class design constraints.
  15-interview-playbook.md     ← 45-minute system design interview strategy.
```

---

## How to Use This Series

1. **Read phase 1 first** — even if you think you know it. Mental models are the gap between
   senior engineer and architect.
2. **Draw every diagram yourself** before reading the solution. Build the muscle.
3. **Reference your own notes** in `notes/system-design/` — this series extends, not replaces them.
4. **Each AI case study** follows the same HLD→LLD template so the pattern becomes instinct.

---

## Quick Reference: Your Existing Strengths → Where to Go Next

| You already know well           | Deep-dive next                               |
|---------------------------------|----------------------------------------------|
| CQRS + Event Sourcing           | 07-async-patterns, 09-rag-system             |
| Kafka                           | 07-async-patterns, 10-ai-agent-platform      |
| AKS + Azure infra               | 08-ai-inference-platform, 11-ml-feature-store|
| Circuit breakers / resilience   | 05-reliability-patterns                      |
| DB sharding + partitioning      | 06-data-patterns, 12-vector-db-deep-dive     |
| Load balancers / CDN / proxies  | 04-scalability-patterns, 08-ai-inference     |
