# Phase 3 — Real World: Design an AI Agent Platform
> Case study: "Design a platform that lets users create AI agents that can use tools, browse the web, write code, and complete multi-step tasks"
> Real examples: LangChain, AutoGPT, OpenAI Assistants API, Devin

---

## What Is an AI Agent?

An AI Agent is an LLM that can:
1. **Observe** its environment (read tools, context, memory)
2. **Reason** about what to do next (LLM decides the next step)
3. **Act** (call a tool, write output, request more info)
4. **Reflect** (evaluate the result, decide to continue or stop)

This is a **loop**, not a single request-response.

```
         ┌──────────────────────────────────────────┐
         │              AGENT LOOP                   │
         │                                          │
         │  User Goal                               │
         │      │                                   │
         │      ▼                                   │
         │  ┌─────────────────────┐                │
         │  │     LLM Reasoning   │◀─── Memory     │
         │  │ "What's the next    │                │
         │  │  step to achieve    │                │
         │  │  the goal?"         │                │
         │  └──────────┬──────────┘                │
         │             │                            │
         │     ┌───────┴───────┐                   │
         │     │               │                   │
         │   TOOL            DONE                  │
         │  CALL              │                    │
         │     │              ▼                    │
         │     ▼          Final Answer             │
         │  Tool Result                            │
         │     │                                   │
         │     └──── Feed back into loop ──────────┘
         └──────────────────────────────────────────┘
```

**Key insight**: Unlike RAG (one pass), agents are loops with tool use, memory, and planning.
This introduces fundamentally different system design challenges.

---

## Requirements Clarification

### Functional Requirements
- Create agents with configurable tools (web search, code execution, file I/O, APIs)
- Execute multi-step tasks asynchronously
- Maintain agent memory across steps and across sessions
- Support multiple concurrent agents per user
- Provide real-time streaming of agent's reasoning and actions
- Handle tool errors and retry logic
- Human-in-the-loop: pause and request user approval for sensitive actions

### Non-Functional Requirements
```
Latency:        Streaming thoughts as they happen (< 200ms to first token)
Duration:       Agent tasks can run 1 minute to 1 hour
Reliability:    Resumable — if infrastructure fails, agent picks up from last checkpoint
Safety:         Code execution sandboxed, no arbitrary shell commands
Scale:          1000 concurrent agents per tenant
Observability:  Full replay of every thought, tool call, and result
Cost:           Track LLM tokens consumed per agent run
```

---

## HLD: The Agent Platform Architecture

```
┌────────────────────────────────────────────────────────────────────────────┐
│                              USER / CLIENT                                 │
│   POST /agents/runs  →  Create a run                                      │
│   GET  /agents/runs/{id}/stream  →  Stream events (SSE)                   │
│   POST /agents/runs/{id}/feedback  →  Provide human approval              │
└───────────────────────────────┬────────────────────────────────────────────┘
                                │
┌───────────────────────────────▼────────────────────────────────────────────┐
│                            API GATEWAY                                     │
│   Auth  │  Rate Limiting  │  Run ID assignment  │  SSE fan-out            │
└───────────────────────────────┬────────────────────────────────────────────┘
                                │
┌───────────────────────────────▼────────────────────────────────────────────┐
│                         RUN ORCHESTRATOR                                   │
│   Creates AgentRun entity in DB                                            │
│   Submits to task queue                                                    │
│   Manages lifecycle (PENDING → RUNNING → WAITING → DONE/FAILED)           │
└─────────────────────────┬──────────────────────────────────────────────────┘
                          │  Kafka message: new run
┌─────────────────────────▼──────────────────────────────────────────────────┐
│                         AGENT WORKER POOL                                  │
│                                                                            │
│  ┌─────────────────────────────────────────────────────────────────────┐  │
│  │                        AGENT RUNNER (per run)                       │  │
│  │                                                                     │  │
│  │  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────────┐  │  │
│  │  │ Memory Svc   │  │  Tool Router │  │   LLM Client             │  │  │
│  │  │ (Redis/DB)   │  │  (dispatch   │  │   (call inference API)   │  │  │
│  │  │              │  │   to tools)  │  │                          │  │  │
│  │  └──────────────┘  └──────────────┘  └──────────────────────────┘  │  │
│  └─────────────────────────────────────────────────────────────────────┘  │
│                                                                            │
│  Max N workers per pod × M pods = concurrent runs                         │
└─────────────────────────┬──────────────────────────────────────────────────┘
                          │
          ┌───────────────┼───────────────────┬────────────────┐
          │               │                   │                │
┌─────────▼──────┐ ┌──────▼──────┐ ┌─────────▼────────┐ ┌────▼────────────┐
│  CODE EXECUTOR │ │ WEB SEARCH  │ │  FILE SERVICE    │ │  EXTERNAL APIs  │
│  (gVisor/K8s   │ │ (Serper/    │ │  (S3, read/write │ │  (Jira, GitHub, │
│   job sandbox) │ │  Brave API) │ │   files)         │ │   Slack, etc.)  │
└────────────────┘ └─────────────┘ └──────────────────┘ └─────────────────┘
                          │
┌─────────────────────────▼──────────────────────────────────────────────────┐
│                         PERSISTENCE LAYER                                  │
│                                                                            │
│  ┌──────────────────┐  ┌────────────────────┐  ┌────────────────────────┐ │
│  │  Run Store       │  │  Event Stream      │  │  Memory Store          │ │
│  │  (PostgreSQL)    │  │  (Kafka)           │  │  (Redis + PG)          │ │
│  │  - AgentRun      │  │  - All agent steps │  │  - Short-term: Redis   │ │
│  │  - AgentStep     │  │  - Tool calls      │  │  - Long-term: PG + VDB │ │
│  │  - Checkpoints   │  │  - Audit log       │  │                        │ │
│  └──────────────────┘  └────────────────────┘  └────────────────────────┘ │
└────────────────────────────────────────────────────────────────────────────┘
                          │
┌─────────────────────────▼──────────────────────────────────────────────────┐
│                    STREAMING / EVENT DELIVERY                              │
│   Kafka → Event Fan-out Service → SSE to connected clients                │
│   (Agent's thoughts streamed in real-time as they happen)                 │
└────────────────────────────────────────────────────────────────────────────┘
```

---

## LLD Deep-Dives

### 1. Agent Run State Machine

This is the core of the orchestration layer.

```
                           ┌─────────────────────┐
                           │   CREATED            │
                           │   (run submitted)    │
                           └──────────┬───────────┘
                                      │ Worker picks up
                           ┌──────────▼───────────┐
                           │   RUNNING            │◀──────────────┐
                           │   (agent loop active)│               │
                           └──────────┬───────────┘               │
                     ┌────────────────┼────────────────────────┐   │
                     │                │                        │   │
           Tool call │        Need    │               Error    │   │
           executing │        human   │                        │   │
                     │        input   │                        │   │
          ┌──────────▼──┐  ┌──────────▼──────┐  ┌────────────▼──┐ │
          │  TOOL_CALL  │  │ AWAITING_INPUT  │  │   RETRYING   │ │
          └──────────┬──┘  └──────────┬──────┘  └────────────┬──┘ │
                     │       User     │           Backoff OK  │   │
           Result    │       provides │                       │   │
           available │       input    └───────────────────────┘   │
                     │                                            │
                     └────────────────────────────────────────────┘
                                      │ Max steps reached
                                      │ or goal achieved
                     ┌────────────────┴────────────────┐
                     │                                 │
          ┌──────────▼──┐                  ┌───────────▼───────┐
          │  COMPLETED  │                  │    FAILED         │
          └─────────────┘                  └───────────────────┘
```

```java
@Entity
public class AgentRun {
    @Id UUID id;
    UUID userId;
    UUID agentConfigId;
    String goal;
    AgentRunStatus status;
    String currentStepId;
    int stepCount;
    int maxSteps;
    int promptTokensUsed;
    int completionTokensUsed;
    Instant createdAt;
    Instant updatedAt;
    Instant completedAt;
    String failureReason;

    // Optimistic locking — prevents concurrent worker updates
    @Version
    int version;
}

public enum AgentRunStatus {
    CREATED, RUNNING, TOOL_CALL, AWAITING_INPUT, RETRYING, COMPLETED, FAILED, CANCELLED
}
```

---

### 2. The Agent Loop — Core Algorithm

The most important code in the entire system. This is the ReAct (Reasoning + Acting) pattern.

```java
@Service
public class AgentRunner {

    private final LlmClient llm;
    private final ToolRouter toolRouter;
    private final MemoryService memory;
    private final AgentRunRepository runRepo;
    private final AgentEventPublisher eventPublisher;

    /**
     * Main agent loop. Implements ReAct pattern:
     *   Thought → Action → Observation → Thought → ...
     *
     * Termination conditions:
     *   1. LLM returns "FINAL ANSWER: ..."
     *   2. maxSteps reached
     *   3. Error after maxRetries
     *   4. User cancels
     */
    public void run(AgentRun run) {
        List<AgentMessage> history = buildInitialMessages(run.getGoal(), run.getAgentConfig());
        int steps = 0;

        while (steps < run.getMaxSteps()) {
            steps++;

            // Update status + checkpoint (for resumability)
            updateStatus(run, AgentRunStatus.RUNNING, steps);
            saveCheckpoint(run, history);

            // 1. LLM decides next action (streaming)
            AgentDecision decision = llm.reason(history, run.getAvailableTools());
            emitThoughtEvent(run, decision.getThought());

            // 2. Check if done
            if (decision.isFinalAnswer()) {
                saveResult(run, decision.getFinalAnswer());
                updateStatus(run, AgentRunStatus.COMPLETED, steps);
                emitDoneEvent(run, decision.getFinalAnswer());
                return;
            }

            // 3. Check if human approval needed
            if (requiresHumanApproval(decision.getToolCall())) {
                updateStatus(run, AgentRunStatus.AWAITING_INPUT, steps);
                emitApprovalRequestEvent(run, decision.getToolCall());
                return; // Worker releases — will be resumed when user responds
            }

            // 4. Execute tool
            ToolResult result;
            try {
                updateStatus(run, AgentRunStatus.TOOL_CALL, steps);
                emitToolCallEvent(run, decision.getToolCall());
                result = toolRouter.execute(decision.getToolCall());
                emitToolResultEvent(run, result);
            } catch (ToolException e) {
                // Tool failed — feed error back to agent (let it recover)
                result = ToolResult.error(e.getMessage());
                emitToolErrorEvent(run, e);
            }

            // 5. Add to history for next iteration
            history.add(AgentMessage.assistantAction(decision));
            history.add(AgentMessage.toolResult(result));
            history = memory.trimHistory(history, run.getContextWindow());
        }

        // Max steps reached
        updateStatus(run, AgentRunStatus.FAILED, steps);
        emitFailureEvent(run, "Max steps reached without completing the goal");
    }
}
```

---

### 3. Tool System — Safe and Extensible

**Tools are the agent's hands. Safety is critical.**

```java
// Tool interface — every tool implements this
public interface AgentTool {
    String getName();
    String getDescription();          // LLM reads this to decide when to use the tool
    JsonSchema getInputSchema();      // LLM uses this to format the tool call
    Mono<ToolResult> execute(JsonNode input, ToolExecutionContext context);
    boolean requiresHumanApproval(JsonNode input);
}

// Tool registry — returns tools available for a given agent config
@Service
public class ToolRouter {

    private final Map<String, AgentTool> tools;

    @Autowired
    public ToolRouter(List<AgentTool> availableTools) {
        this.tools = availableTools.stream()
            .collect(Collectors.toMap(AgentTool::getName, Function.identity()));
    }

    public Mono<ToolResult> execute(ToolCall call) {
        AgentTool tool = tools.get(call.getToolName());
        if (tool == null) {
            return Mono.just(ToolResult.error("Unknown tool: " + call.getToolName()));
        }

        // Validate input against schema before executing
        List<String> validationErrors = validateInput(call.getInput(), tool.getInputSchema());
        if (!validationErrors.isEmpty()) {
            return Mono.just(ToolResult.error("Invalid input: " + validationErrors));
        }

        return tool.execute(call.getInput(), buildContext(call))
            .timeout(Duration.ofSeconds(30))
            .onErrorReturn(ToolResult.error("Tool timed out"));
    }
}

// Example: Code execution tool (sandboxed!)
@Component
public class CodeExecutionTool implements AgentTool {

    private final KubernetesClient k8sClient;

    @Override
    public String getName() { return "execute_python"; }

    @Override
    public boolean requiresHumanApproval(JsonNode input) {
        // Always require approval if code touches filesystem or network
        String code = input.path("code").asText();
        return code.contains("os.system") ||
               code.contains("subprocess") ||
               code.contains("socket") ||
               code.contains("open(") && code.contains("w");
    }

    /**
     * Executes code in a disposable Kubernetes Job with:
     * - No network access (NetworkPolicy blocks egress)
     * - No filesystem access outside /tmp (ReadOnlyRootFilesystem)
     * - CPU/memory limits
     * - 30s timeout
     * - gVisor runtime (kernel-level sandboxing)
     */
    @Override
    public Mono<ToolResult> execute(JsonNode input, ToolExecutionContext ctx) {
        String code = input.path("code").asText();

        return k8sClient.createCodeExecutionJob(
            CodeExecutionJob.builder()
                .code(code)
                .language("python3.11")
                .cpuLimit("500m")
                .memoryLimit("256Mi")
                .timeoutSeconds(30)
                .runtimeClass("gvisor")     // Sandboxed kernel
                .networkPolicy("deny-all")  // No network access
                .build()
        )
        .map(jobResult -> ToolResult.success(jobResult.getOutput()))
        .onErrorMap(e -> new ToolException("Code execution failed: " + e.getMessage()));
    }
}
```

---

### 4. Memory System — Short-term and Long-term

**Challenge**: Agents run for many steps. Context grows. LLMs have token limits.

```
Memory Types:

1. IN-CONTEXT MEMORY (working memory)
   The current message history sent with each LLM call.
   Limited by context window.
   Managed by trimming oldest messages.

2. SHORT-TERM EXTERNAL MEMORY (session memory)
   Redis: key=run_id, value=compressed summary of earlier steps
   Summarized periodically when in-context memory fills up

3. LONG-TERM MEMORY (cross-session)
   Vectorized memories stored in PostgreSQL + pgvector
   Semantically retrieved at run start
   Example: "Last time this user ran a data analysis task, they preferred pandas over polars"

4. EPISODIC MEMORY (run checkpoint)
   Full serialized state stored in PostgreSQL
   Enables resumability after worker failure or human-in-the-loop pauses
```

```java
@Service
public class MemoryService {

    private final ReactiveRedisTemplate<String, String> redis;
    private final AgentRunRepository runRepo;
    private final EmbeddingService embeddingService;
    private final MemoryVectorRepository vectorRepo;

    /**
     * Retrieves relevant long-term memories for a new run.
     * These are injected as a system message: "From previous sessions: ..."
     */
    public Mono<List<Memory>> retrieveRelevantMemories(String userId, String goal) {
        return embeddingService.embed(goal)
            .flatMap(embedding -> vectorRepo.findSimilar(userId, embedding, 5))
            .map(chunks -> chunks.stream()
                .filter(m -> m.getRelevanceScore() > 0.75)
                .collect(toList()));
    }

    /**
     * Saves important observations as long-term memories at end of run.
     * Uses LLM to extract key learnings from the run.
     */
    public Mono<Void> consolidateMemories(AgentRun run, List<AgentMessage> history) {
        // Ask LLM: "What are the 3 most important facts learned in this session?"
        return llm.extractMemories(history)
            .flatMap(memories -> Flux.fromIterable(memories)
                .flatMap(memory -> embeddingService.embed(memory.getContent())
                    .flatMap(embedding -> vectorRepo.save(
                        Memory.builder()
                            .userId(run.getUserId())
                            .content(memory.getContent())
                            .embedding(embedding)
                            .sourceRunId(run.getId())
                            .createdAt(Instant.now())
                            .build()))
                )
                .then());
    }
}
```

---

### 5. Streaming Agent Events — Real-time Visibility

Users need to see what the agent is doing. Every thought, tool call, and result is an event.

```java
public sealed interface AgentEvent {
    record ThoughtEvent(String runId, String thought, Instant ts) implements AgentEvent {}
    record ToolCallEvent(String runId, String tool, JsonNode input, Instant ts) implements AgentEvent {}
    record ToolResultEvent(String runId, String tool, String result, Instant ts) implements AgentEvent {}
    record AwaitingApprovalEvent(String runId, String toolName, String reason) implements AgentEvent {}
    record CompletedEvent(String runId, String answer, TokenUsage usage) implements AgentEvent {}
    record FailedEvent(String runId, String reason) implements AgentEvent {}
}

// Kafka → Redis Pub/Sub → SSE to clients
@RestController
public class AgentStreamController {

    private final ReactiveRedisTemplate<String, String> redis;

    @GetMapping(value = "/agents/runs/{runId}/stream",
                produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamRun(@PathVariable String runId,
                                                   Authentication auth) {
        // Subscribe to Redis pub/sub channel for this run
        return redis.listenToChannel("agent-events:" + runId)
            .map(message -> ServerSentEvent.<String>builder()
                .id(UUID.randomUUID().toString())
                .data(message.getMessage())
                .build())
            .timeout(Duration.ofMinutes(60))    // Max run duration
            .takeUntil(event -> isTerminalEvent(event)); // Stop streaming on done/failed
    }
}
```

---

## Key Differences: Agent vs RAG vs Simple LLM

| Dimension            | Simple LLM API         | RAG System             | AI Agent                     |
|----------------------|------------------------|------------------------|------------------------------|
| Execution model      | Single request         | Single request (2-step)| Loop (N steps)               |
| Duration             | < 5 seconds            | < 10 seconds           | Minutes to hours             |
| State                | Stateless              | Read-only context      | Stateful (changes world)     |
| Error handling       | Retry same request     | Retry retrieval        | Agent recovers and adapts    |
| Infrastructure cost  | GPU per request        | GPU + vector DB        | GPU × N + tools + sandbox    |
| Safety concerns      | Low                    | Medium (data isolation)| High (code exec, API calls)  |
| Resumability needed  | No                     | No                     | Yes (can run for hours)      |

---

## Tradeoffs

| Decision                       | Choice                    | Tradeoff                                     |
|--------------------------------|---------------------------|----------------------------------------------|
| Kafka for run events           | Async, durable            | Adds latency vs direct SSE; gains replay      |
| k8s Job for code sandbox       | Full isolation            | Cold start ~2s; but critical for safety       |
| ReAct loop (not planning-first)| Simple, flexible          | Less optimal planning; but easier to debug    |
| Redis for streaming            | Pub/Sub is simple         | Not persistent; replay requires Kafka         |
| Human-in-the-loop pauses       | Stateful checkpoint       | Complex resume logic; but essential for trust |

---

**Next**: [11-ml-feature-store.md] — Design a Feature Store for ML pipelines.
