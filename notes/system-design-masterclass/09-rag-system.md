# Phase 3 — Real World: Design a Production RAG System
> Case study: "Design a RAG system that lets users chat with their company's documents"
> Real examples: GitHub Copilot Workspace, Notion AI, Confluence AI, Glean

---

## What Is RAG and Why Does It Exist?

**The Problem with Pure LLMs**:
```
LLM's knowledge is frozen at training cutoff.
LLMs hallucinate facts they don't know.
LLMs can't access your private data.
LLMs have context window limits (can't fit 10,000 docs).
```

**RAG Solution**:
```
At query time:
  1. Convert user question to a vector (semantic embedding)
  2. Search a vector database for semantically similar document chunks
  3. Inject the retrieved chunks into the LLM's context
  4. LLM generates answer grounded in real, current documents
```

```
WITHOUT RAG:
  User: "What is our Q3 revenue target?"
  LLM:  "I don't have access to your financial data."
        or worse: hallucinates a number

WITH RAG:
  1. Embed: "Q3 revenue target" → [0.23, -0.45, 0.88, ...]
  2. Search: Find top 3 chunks in vector DB that match
  3. Retrieve: "From Q3 planning doc: target is £45M ARR by Sept 30"
  4. LLM:  "Based on your Q3 planning document, the revenue target is £45M ARR by September 30."
```

---

## Requirements Clarification

### Functional Requirements
- Ingest documents (PDF, Word, markdown, HTML, Confluence pages)
- Process and index documents into a searchable vector store
- Accept user questions in natural language
- Return answers with source citations
- Support filtering by document type, date, team/project
- Multi-tenant (each customer's data isolated)

### Non-Functional Requirements
```
Query latency:       < 2s end-to-end (embedding + retrieval + generation)
Ingest throughput:   100 documents/minute (new documents added continuously)
Scale:               1M documents per tenant, 100 tenants = 100M documents total
Availability:        99.9%
Data isolation:      Tenant A cannot access Tenant B's data (critical!)
Freshness:           New document indexed and queryable within 5 minutes of upload
```

---

## The Two Pipelines

RAG has two distinct pipelines. This is the most important thing to understand.

```
INDEXING PIPELINE (offline/async)          QUERY PIPELINE (online/real-time)
─────────────────────────────────          ──────────────────────────────────────
Raw Document                               User Question
     │                                          │
     ▼                                          ▼
  [Parse + Clean]                          [Embed Question]
     │                                          │
     ▼                                          ▼
  [Chunk]                                  [Vector Search]
     │                                          │
     ▼                                          ▼
  [Embed Each Chunk]                       [Retrieve Top-K Chunks]
     │                                          │
     ▼                                          ▼
  [Store in Vector DB]                     [Re-rank Chunks]
     │                                          │
     ▼                                          ▼
  [Store metadata in SQL]                  [Build Context Window]
                                                │
                                                ▼
                                           [LLM Generation]
                                                │
                                                ▼
                                           Answer + Citations
```

---

## HLD: The Full Architecture

```
                    ┌───────────────────────────────────────────┐
                    │            DOCUMENT SOURCES               │
                    │  S3  │  Confluence  │  Google Drive  │ DB  │
                    └───────────────────┬───────────────────────┘
                                        │  (webhooks / polling)
         ┌──────────────────────────────▼──────────────────────────────────┐
         │                    INGESTION PIPELINE                           │
         │                                                                 │
         │  ┌─────────────┐   ┌──────────────┐   ┌────────────────────┐  │
         │  │ Doc Fetcher  │──▶│ Doc Parser   │──▶│ Chunking Engine    │  │
         │  │ (connector   │   │ (PDF→text,   │   │ (split into 512-   │  │
         │  │  per source) │   │  DOCX→text)  │   │  token chunks)     │  │
         │  └─────────────┘   └──────────────┘   └────────┬───────────┘  │
         │                                                 │              │
         │                    ┌────────────────────────────▼───────────┐  │
         │                    │         EMBEDDING SERVICE              │  │
         │                    │  Batch embed chunks (text-embedding-3) │  │
         │                    │  Output: [chunk_id, vector[1536]]      │  │
         │                    └────────────────────────────┬───────────┘  │
         │                                                 │              │
         │          ┌──────────────────┐    ┌─────────────▼────────────┐  │
         │          │  PostgreSQL      │◀───│   Vector Database        │  │
         │          │  (metadata:      │    │   (Pinecone / pgvector / │  │
         │          │  tenant, doc,    │    │    Qdrant / Weaviate)    │  │
         │          │  chunk, source)  │    │   Index: tenant + vector │  │
         │          └──────────────────┘    └──────────────────────────┘  │
         └─────────────────────────────────────────────────────────────────┘

                    ┌───────────────────────────────────────────┐
                    │              USER / CLIENT                │
                    └───────────────────┬───────────────────────┘
                                        │
         ┌──────────────────────────────▼──────────────────────────────────┐
         │                      QUERY PIPELINE                             │
         │                                                                 │
         │  ┌───────────────┐  ┌────────────────┐  ┌───────────────────┐  │
         │  │ Query API     │  │ Query Processor │  │ Embedding Service  │  │
         │  │ (REST + auth) │─▶│ (decompose,     │─▶│ (embed question)  │  │
         │  │               │  │  expand query)  │  │                   │  │
         │  └───────────────┘  └────────────────┘  └────────┬──────────┘  │
         │                                                   │             │
         │                           ┌───────────────────────▼───────────┐  │
         │                           │      RETRIEVAL ENGINE              │  │
         │                           │  ANN search (cosine similarity)    │  │
         │                           │  Filter by: tenant_id, date, type  │  │
         │                           │  Return top-20 candidates          │  │
         │                           └───────────────────────┬───────────┘  │
         │                                                   │             │
         │                           ┌───────────────────────▼───────────┐  │
         │                           │         RE-RANKER                  │  │
         │                           │  Cross-encoder reranks top-20      │  │
         │                           │  Returns top-5 most relevant       │  │
         │                           └───────────────────────┬───────────┘  │
         │                                                   │             │
         │                           ┌───────────────────────▼───────────┐  │
         │                           │       LLM GENERATION               │  │
         │                           │  Build prompt: question + chunks   │  │
         │                           │  Stream response with citations    │  │
         │                           └───────────────────────────────────┘  │
         └─────────────────────────────────────────────────────────────────┘
```

---

## LLD Deep-Dives

### 1. Chunking Strategy — The Most Underrated Decision

**Why chunking matters**: The chunk is the atomic unit of retrieval.
Too big → retrieves irrelevant context, wastes token budget.
Too small → loses context needed to answer the question.

```
Chunking strategies:

1. FIXED SIZE (naive)
   Split every 512 tokens, overlap 50 tokens
   Problem: splits mid-sentence, mid-table, mid-code block

2. SEMANTIC (smart)
   Split at paragraph/section boundaries
   Use markdown headers, HTML tags, sentence endings as split points
   Better: preserves meaning

3. HIERARCHICAL (best for structured docs)
   Chunk at multiple levels:
   - Level 1: document summary (1-2 paragraphs)
   - Level 2: section summaries
   - Level 3: paragraph-level chunks
   Query at level 1/2, return level 3 for context
   "Small-to-big" retrieval: match at small chunk level, return parent

4. SENTENCE WINDOW (for precise retrieval)
   Store individual sentences, but at retrieval time
   return surrounding ±2 sentences as context
```

```java
@Service
public class DocumentChunker {

    private static final int CHUNK_SIZE_TOKENS = 512;
    private static final int OVERLAP_TOKENS = 64;

    /**
     * Splits a document into chunks preserving semantic boundaries.
     *
     * Strategy: sentence-aware splitting with overlap.
     * Overlap prevents losing context at chunk boundaries.
     */
    public List<DocumentChunk> chunk(ParsedDocument doc) {
        List<String> sentences = splitIntoSentences(doc.getContent());

        List<DocumentChunk> chunks = new ArrayList<>();
        List<String> currentChunk = new ArrayList<>();
        int currentTokens = 0;

        for (String sentence : sentences) {
            int sentenceTokens = estimateTokens(sentence);

            if (currentTokens + sentenceTokens > CHUNK_SIZE_TOKENS && !currentChunk.isEmpty()) {
                // Save current chunk
                chunks.add(buildChunk(doc, currentChunk, chunks.size()));

                // Overlap: keep last few sentences for context continuity
                int overlapTokens = 0;
                Deque<String> overlap = new ArrayDeque<>();
                for (int i = currentChunk.size() - 1; i >= 0; i--) {
                    String s = currentChunk.get(i);
                    overlapTokens += estimateTokens(s);
                    if (overlapTokens >= OVERLAP_TOKENS) break;
                    overlap.addFirst(s);
                }
                currentChunk = new ArrayList<>(overlap);
                currentTokens = overlapTokens;
            }

            currentChunk.add(sentence);
            currentTokens += sentenceTokens;
        }

        if (!currentChunk.isEmpty()) {
            chunks.add(buildChunk(doc, currentChunk, chunks.size()));
        }

        return chunks;
    }

    private DocumentChunk buildChunk(ParsedDocument doc, List<String> sentences, int index) {
        return DocumentChunk.builder()
            .id(UUID.randomUUID())
            .documentId(doc.getId())
            .tenantId(doc.getTenantId())
            .content(String.join(" ", sentences))
            .chunkIndex(index)
            .sourceUrl(doc.getSourceUrl())
            .metadata(doc.getMetadata())
            .build();
    }
}
```

---

### 2. Vector Database — Schema and Multi-tenancy

**Critical requirement**: Tenant A must NEVER see Tenant B's documents.
**Solution**: Tenant ID as a mandatory filter on every query + namespace/collection per tenant.

```
Option A: Namespace per tenant (Pinecone / Qdrant)
  - One namespace = one tenant
  - Queries are namespace-scoped (hard isolation)
  - Cons: hard to do cross-tenant analytics

Option B: Shared collection + metadata filter (pgvector)
  - All vectors in one table, with tenant_id column
  - Every query filters WHERE tenant_id = ?
  - Risk: missing the filter = data leak bug
  - Mitigation: query helper that always injects tenant_id

Option C: Separate collection per tenant (Weaviate)
  - Physical separation, no filter needed
  - Higher operational overhead

For a B2B SaaS: Option A or C. Never trust the filter alone.
```

**pgvector schema** (if using PostgreSQL):

```sql
-- Enable vector extension
CREATE EXTENSION IF NOT EXISTS vector;

-- Document chunks table
CREATE TABLE document_chunks (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id       UUID NOT NULL,
    document_id     UUID NOT NULL,
    chunk_index     INT NOT NULL,
    content         TEXT NOT NULL,
    embedding       vector(1536),    -- OpenAI text-embedding-3-small dimension
    metadata        JSONB,
    created_at      TIMESTAMPTZ DEFAULT NOW(),

    -- Tenant isolation at DB level (Row Level Security)
    CONSTRAINT tenant_must_match CHECK (tenant_id IS NOT NULL)
);

-- Enable RLS (Row Level Security) — tenant isolation enforcement
ALTER TABLE document_chunks ENABLE ROW LEVEL SECURITY;

CREATE POLICY tenant_isolation ON document_chunks
    USING (tenant_id = current_setting('app.tenant_id')::UUID);

-- ANN index for fast similarity search
-- HNSW index: approximate nearest neighbour, fast at query time
CREATE INDEX idx_chunk_embedding ON document_chunks
    USING hnsw (embedding vector_cosine_ops)
    WITH (m = 16, ef_construction = 64);

-- Composite index for filtered queries
CREATE INDEX idx_tenant_doc ON document_chunks (tenant_id, document_id);
```

---

### 3. Query Expansion — Making Retrieval Smarter

**Problem**: Users ask vague questions. Naive vector search misses relevant chunks.

```
User asks: "How do I get time off?"
Simple search finds: "vacation", "holiday", "PTO"
But misses: "annual leave policy", "leave of absence", "sick leave"

Solution: Query expansion
```

```java
@Service
public class QueryProcessor {

    private final EmbeddingService embeddingService;
    private final LlmService llm;

    /**
     * Expands and enriches the user query before vector search.
     *
     * Techniques:
     * 1. HyDE (Hypothetical Document Embeddings): generate a hypothetical answer
     *    and embed that — often higher quality retrieval
     * 2. Multi-query: generate 3 paraphrases, search with all, union results
     * 3. Query decomposition: break complex questions into sub-questions
     */
    public Mono<List<float[]>> expandAndEmbed(String userQuery, TenantContext tenant) {

        // Strategy 1: embed the original query
        Mono<float[]> directEmbedding = embeddingService.embed(userQuery);

        // Strategy 2: HyDE — ask LLM to generate what the answer might look like
        // Then embed that hypothetical answer — often better semantic match
        Mono<float[]> hydeEmbedding = llm.generateHypotheticalAnswer(userQuery)
            .flatMap(embeddingService::embed);

        // Strategy 3: Query expansion — generate alternative phrasings
        Mono<List<float[]>> expandedEmbeddings = llm.generateQueryVariants(userQuery, 2)
            .flatMap(variants -> Flux.fromIterable(variants)
                .flatMap(embeddingService::embed)
                .collectList());

        return Mono.zip(directEmbedding, hydeEmbedding, expandedEmbeddings)
            .map(tuple -> {
                List<float[]> all = new ArrayList<>();
                all.add(tuple.getT1());
                all.add(tuple.getT2());
                all.addAll(tuple.getT3());
                return all;
            });
    }
}
```

---

### 4. Re-ranking — The Quality Filter

**Why re-ranking?**: Vector search uses bi-encoders (fast but approximate).
Re-ranking uses cross-encoders (slow but accurate). Run cheap search first, then expensive re-rank.

```
Step 1: ANN Vector Search → Top 20 candidates (fast, ~5ms)
Step 2: Cross-encoder re-rank → Top 5 (slower, ~50ms, but much more accurate)
Step 3: Feed Top 5 to LLM

Cross-encoder: takes (query, chunk) pair → relevance score 0-1
More accurate because it compares them together, not independently
```

```java
@Service
public class RerankingService {

    private final CrossEncoderClient crossEncoder;

    /**
     * Re-ranks retrieved chunks using a cross-encoder model.
     * Cross-encoders are slower but significantly more accurate than bi-encoders.
     *
     * Input:  top-20 chunks from ANN search
     * Output: top-5 chunks by relevance to query
     */
    public Mono<List<RankedChunk>> rerank(String query, List<DocumentChunk> candidates) {
        List<ScoringRequest> pairs = candidates.stream()
            .map(chunk -> new ScoringRequest(query, chunk.getContent()))
            .collect(toList());

        return crossEncoder.scoreAll(pairs)
            .map(scores -> zipWithScores(candidates, scores))
            .map(ranked -> ranked.stream()
                .sorted(Comparator.comparingDouble(RankedChunk::getScore).reversed())
                .limit(5)
                .collect(toList()));
    }
}
```

---

### 5. Citation System — Grounding Answers

**Why citations matter**: Users need to verify the LLM's answer. Without citations, you lose trust.

```java
/**
 * Builds the final prompt for the LLM including retrieved context and
 * instructions to cite sources.
 */
public class PromptBuilder {

    private static final String SYSTEM_PROMPT = """
        You are a helpful assistant that answers questions based on provided documents.

        Rules:
        1. Only answer based on the provided context. Do not use outside knowledge.
        2. For every claim, cite the source using [Source N] notation.
        3. If the answer is not in the context, say "I couldn't find this in the documents."
        4. Be concise and direct.
        """;

    public String buildPrompt(String query, List<RankedChunk> chunks) {
        StringBuilder context = new StringBuilder();

        for (int i = 0; i < chunks.size(); i++) {
            RankedChunk chunk = chunks.get(i);
            context.append(String.format("[Source %d] From: %s\n%s\n\n",
                i + 1,
                chunk.getSourceUrl(),
                chunk.getContent()));
        }

        return SYSTEM_PROMPT + "\n\nContext:\n" + context + "\n\nQuestion: " + query;
    }

    /**
     * Extracts citations from LLM response and maps them to actual source URLs.
     * Output: answer text + list of cited documents for UI to render as links.
     */
    public AnswerWithCitations parseResponse(String llmResponse, List<RankedChunk> chunks) {
        // Parse [Source N] markers from response
        Pattern pattern = Pattern.compile("\\[Source (\\d+)\\]");
        Matcher matcher = pattern.matcher(llmResponse);

        Set<Integer> citedIndices = new TreeSet<>();
        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1)) - 1;
            if (index >= 0 && index < chunks.size()) {
                citedIndices.add(index);
            }
        }

        List<Citation> citations = citedIndices.stream()
            .map(i -> Citation.builder()
                .index(i + 1)
                .sourceUrl(chunks.get(i).getSourceUrl())
                .documentTitle(chunks.get(i).getDocumentTitle())
                .snippet(chunks.get(i).getContent().substring(0, Math.min(200, chunks.get(i).getContent().length())))
                .build())
            .collect(toList());

        return new AnswerWithCitations(llmResponse, citations);
    }
}
```

---

## Key Tradeoffs

| Decision               | Choice               | Tradeoff                                            |
|------------------------|----------------------|-----------------------------------------------------|
| Chunk size: 512 tokens | Medium chunks        | Smaller = more precise retrieval; larger = more context |
| pgvector vs Pinecone   | pgvector (start)     | Less operational overhead; add Pinecone at 100M+ vectors |
| Re-ranking (cross-enc) | Always on            | Adds 50-100ms but dramatically improves answer quality |
| HyDE query expansion   | Optional (A/B test)  | Better recall, but adds 200ms and one LLM call |
| Tenant isolation: RLS  | DB-level enforcement | Safer than app-level filtering; slight perf cost |

---

## Failure Modes Specific to RAG

```
1. RETRIEVAL FAILURE — "I couldn't find anything relevant"
   Cause: Document not indexed yet, bad chunking, query too ambiguous
   Fix: Return "no results" message + suggested query reformulation

2. CONTEXT STUFFING FAILURE — LLM ignores retrieved context
   Cause: Context window too full, LLM over-relying on parametric knowledge
   Fix: Use instruction: "Your answer MUST be based on the provided context only"

3. STALE DATA — Answer is outdated
   Cause: Document updated but re-indexing delayed
   Fix: Show "last updated" timestamp with citations; webhook-triggered re-indexing

4. CROSS-CHUNK ANSWER — Answer spans multiple chunks
   Cause: Information split across chunk boundaries
   Fix: Increase overlap; use hierarchical chunking; retrieve more chunks

5. TENANT DATA LEAK — Critical failure
   Cause: Missing tenant_id filter, RLS not set correctly
   Fix: Row Level Security in DB; integration tests that verify isolation
```

---

## Observability for RAG

```
Retrieval Quality Metrics:
  - Hit rate: % of queries where top-5 contained the correct answer
  - MRR (Mean Reciprocal Rank): how high up the correct chunk was ranked
  - NDCG: normalized quality score of ranked list

System Performance Metrics:
  - Embedding latency (p99)
  - Vector search latency (p99)
  - Re-ranking latency (p99)
  - End-to-end answer latency (p99)

User Feedback Metrics (most valuable):
  - Thumbs up/down per answer
  - "Was this helpful?" click rate
  - Fallback rate (% "I couldn't find this" answers)
```

---

**Next**: [10-ai-agent-platform.md] — Design an AI Agent orchestration platform.
