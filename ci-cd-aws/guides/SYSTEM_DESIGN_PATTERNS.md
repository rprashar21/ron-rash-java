# 🏗️ System Design Patterns: DevOps in Practice

Apply mental models to real system architectures.

---

## 1. The Complete Architecture: From Code to Running App

### Single Developer → Enterprise System

**Week 1: You write Python app**
```
┌──────────────────────────────┐
│    Your Laptop               │
├──────────────────────────────┤
│ Python app                   │
│ + local SQLite database      │
│ + hardcoded config           │
│                              │
│ "Works on my machine!" ✓     │
└──────────────────────────────┘
```

**Month 1: Add CI/CD**
```
┌──────────────────┐     ┌──────────────────────────────┐
│ Your Laptop      │     │ GitHub Actions               │
├──────────────────┤     ├──────────────────────────────┤
│ Push code        │────→│ Test automatically           │
│ Tests pass ✓     │     │ Build Docker image           │
│                  │     │ Deploy to AWS Lambda         │
└──────────────────┘     └──────────────────────────────┘
                                      ↓
                         ┌──────────────────────────────┐
                         │ AWS Lambda                   │
                         ├──────────────────────────────┤
                         │ Your app running in prod!    │
                         │ Handling real requests       │
                         └──────────────────────────────┘
```

**Year 1: Enterprise System**
```
┌────────────────────────────────────────────────────────────┐
│                    DEVELOPER WORKFLOW                      │
├────────────────────────────────────────────────────────────┤
│                                                            │
│  Your IDE → git push → GitHub (repo)                      │
│                         ↓                                  │
│                 GitHub Actions (CI)                        │
│                ├─ Lint (10 sec)                           │
│                ├─ Unit Tests (30 sec)                     │
│                ├─ Integration Tests (1 min)               │
│                ├─ Build Docker (2 min)                    │
│                └─ Push to ECR (10 sec)                    │
│                         ↓ PASS                            │
│              Deploy to Staging Environment                │
│                ├─ ECS Cluster (3 containers)             │
│                ├─ PostgreSQL (RDS)                       │
│                ├─ Redis Cache                            │
│                └─ Load Balancer                          │
│                         ↓                                  │
│              Team Reviews Staging                         │
│                ├─ Manual testing                         │
│                ├─ Performance verification               │
│                └─ Approval                               │
│                         ↓                                  │
│             Deploy to Production                          │
│                ├─ Blue-Green Deployment                  │
│                ├─ 5% → 50% → 100% rollout               │
│                ├─ Auto-rollback if errors               │
│                └─ Monitoring & Alerts                    │
│                         ↓                                  │
│               CloudWatch Monitoring                       │
│                ├─ Logs (1000s/sec)                       │
│                ├─ Metrics (CPU, memory, latency)         │
│                ├─ Alerts (Slack, PagerDuty)             │
│                └─ Dashboards                             │
│                         ↓                                  │
│              Production Running Safely                    │
│                ├─ 99.99% uptime                          │
│                ├─ Auto-scaling                           │
│                ├─ Disaster recovery                      │
│                └─ User happy!                            │
│                                                            │
└────────────────────────────────────────────────────────────┘
```

---

## 2. The Three Tiers of Deployment Complexity

### Tier 1: Simple (Lambda/Serverless)
**For**: APIs, microservices, event handlers
**Complexity**: Low
**Cost**: Pay per execution

```
GitHub Actions
    ↓
Build Docker
    ↓
Push to ECR
    ↓
AWS Lambda
  ├─ Update function code
  ├─ Cold start on first request
  └─ Scale automatically

Architecture:
Client → API Gateway → Lambda → Database
                       (scales 0→1000 instantly)
```

### Tier 2: Moderate (Container Orchestration)
**For**: Web apps, data processing, internal tools
**Complexity**: Medium
**Cost**: Fixed hourly per container

```
GitHub Actions
    ↓
Build Docker
    ↓
Push to ECR
    ↓
ECS (Elastic Container Service)
  ├─ 3-5 containers running
  ├─ Load balancer routes traffic
  ├─ Auto-scaling rules
  └─ Rolling deployments

Architecture:
Client → Load Balancer → ECS Cluster (3 containers)
                            ├─ Container 1
                            ├─ Container 2
                            └─ Container 3
         → RDS (Database)
         → ElastiCache (Redis)
```

### Tier 3: Complex (Multi-Region, Kubernetes)
**For**: Critical services, high-performance apps
**Complexity**: High
**Cost**: $$$ (infrastructure + management)

```
GitHub Actions
    ↓
Build Docker
    ↓
Multi-Registry Push (ECR, DockerHub)
    ↓
Kubernetes Deployment
  ├─ Multiple regions
  ├─ Service mesh (Istio)
  ├─ Advanced deployments (canary, A/B test)
  ├─ Auto-scaling based on metrics
  └─ Self-healing

Architecture:
┌────────────────────────────────────────┐
│         KUBERNETES CLUSTER             │
├────────────────────────────────────────┤
│                                        │
│  Pods: ├─ App Pod 1                   │
│        ├─ App Pod 2                   │
│        ├─ App Pod 3                   │
│        ├─ Database Pod                │
│        └─ Cache Pod                   │
│                                        │
│  Services: ├─ Load Balancer           │
│            ├─ Service Discovery       │
│            └─ Auto-scaling            │
│                                        │
│  Storage: ├─ PersistentVolumes        │
│           └─ StatefulSets             │
│                                        │
│  Monitoring: ├─ Prometheus             │
│              ├─ Grafana                │
│              └─ AlertManager           │
│                                        │
└────────────────────────────────────────┘

Multi-Region:
┌─────────────────┐  ┌─────────────────┐
│  US East        │  │  EU West        │
│  Cluster 1      │  │  Cluster 2      │
└────────┬────────┘  └────────┬────────┘
         │                     │
         └──────── Global ─────┘
                    Load Balancer
```

---

## 3. Architectural Patterns for CI/CD

### Pattern 1: Push-Based Deployment
**How**: GitHub Actions pushes changes to AWS

```
Developer Push
    ↓
GitHub Actions (What to deploy)
    ├─ Build image: hello-app:v1.2.3
    ├─ Push to ECR
    └─ Call AWS API: "Update Lambda with this image"
    ↓
AWS (Receives update)
    ├─ Pull image from ECR
    ├─ Stop old version
    ├─ Start new version
    └─ Verify health

Pros:
✓ Simple (centralized control)
✓ Fast (direct push)

Cons:
✗ GitHub Actions needs AWS credentials
✗ One-way communication (hard to verify success)
```

### Pattern 2: Pull-Based Deployment (GitOps)
**How**: AWS continuously watches GitHub and pulls changes

```
Developer Push
    ↓
GitHub Repository
    └─ Image digest committed to git
    ↓
AWS (ArgoCD watching repo)
    ├─ Detects change in git
    ├─ Pulls latest image
    └─ Applies to cluster

Pros:
✓ Audit trail (all changes in git)
✓ Safe (only specified images deployed)
✓ Declarative (desired state in git)

Cons:
✗ More complex setup
✗ Slight delay (polling)

This is the GitOps pattern!
```

### Pattern 3: Event-Driven Deployment
**How**: Events trigger deployments

```
Developer Push
    ↓
GitHub sends webhook
    ↓
AWS EventBridge (listens for webhooks)
    ├─ "New Docker image available"
    ├─ Triggers Lambda function
    └─ Lambda updates ECS/K8s
    ↓
Application updates

Pros:
✓ Real-time (instant response)
✓ Decoupled (services don't know about each other)
✓ Scalable (EventBridge handles scale)

Cons:
✗ Eventual consistency (no guarantee of order)
✗ Debugging harder
```

---

## 4. Database & Stateful Systems in CI/CD

### Problem: How to Deploy Database Changes?

```
Traditional Approach:
1. Developer writes code
2. Developer writes SQL migration
3. Manual deployment (DBA runs migration)
4. Wait for approval
5. Risk of data loss

CI/CD Approach:
1. Developer writes code + migration
2. Migration auto-runs in test environment
3. Tests verify it works
4. Auto-deployed to prod with versioning
```

### Database Migration in Pipeline
```
Pipeline:
  ├─ Checkout code (includes migration files)
  ├─ Spin up PostgreSQL in Docker
  ├─ Run migrations: alembic upgrade head
  ├─ Run tests against new schema
  └─ If PASS: Deploy to production

Production Deployment:
  ├─ Create backup of database
  ├─ Run migration: alembic upgrade head
  ├─ Verify data integrity
  ├─ Rollback plan: alembic downgrade -1
  └─ Monitor for errors

Key Insight:
Treat migrations like code!
├─ Version controlled
├─ Tested
├─ Reversible
└─ Automated
```

---

## 5. Configuration Management in CI/CD

### Problem: Different configs for dev/test/prod

```
❌ BAD Approach:
├─ Hardcode database URL in code
├─ Hardcode API keys in Dockerfile
├─ Different code for different environments
└─ Secrets exposed in git history

✓ GOOD Approach:
├─ Code is same everywhere
├─ Configuration injected at runtime
├─ Secrets from secure vault
├─ Environment-specific configs in git-ignored files
```

### Configuration Pattern
```
Application Structure:
├─ src/app.py (code, no secrets)
├─ config/default.yml (common config)
├─ config/dev.yml (dev-specific)
├─ config/prod.yml (prod-specific)
└─ .env (NEVER commit, local only)

At Runtime:
  ├─ Load config/default.yml
  ├─ Overlay environment-specific config
  ├─ Override with environment variables
  └─ Final config used

Pipeline Approach:
  GitHub Actions:
    ├─ Set secrets as env vars
    └─ Pass to Docker: -e VAR=${{ secrets.VAR }}

  Docker:
    ├─ Read environment variables
    └─ Load configuration

  AWS:
    ├─ Set parameters in Lambda
    ├─ Or use Secrets Manager
    └─ Or use Parameter Store
```

---

## 6. Testing Strategy in CI/CD

### The Testing Pyramid
```
             ▲
            /|\
           / | \
          /  |  \
         / E2E  \      (5-10 tests, slow, brittle)
        /________|____
       /         |    \
      /Integration\    \  (20-50 tests, medium speed)
     /___________\__|_____
    /             |       \
   / Unit Tests   |        \ (100-1000 tests, fast)
  /________________|__________\

Composition:
├─ 70% Unit tests (test individual functions)
├─ 20% Integration tests (test components together)
└─ 10% E2E tests (test full user flow)

Why? Unit tests are:
├─ Fastest (milliseconds)
├─ Most reliable (deterministic)
├─ Easiest to write
└─ Cover 80% of bugs

E2E tests are:
├─ Slowest (seconds to minutes)
├─ Flaky (non-deterministic)
├─ Hard to maintain
└─ But catch critical bugs
```

### Test Execution in Pipeline
```
All tests run in parallel:

┌─────────────────────────────────────┐
│      Test Job (5 min total)         │
├─────────────────────────────────────┤
│                                     │
│  Time 0:                            │
│  ├─ Unit tests start                │
│  ├─ Integration tests start         │
│  └─ Lint start                      │
│                                     │
│  Time 0-1 min:                      │
│  ├─ Unit tests finish ✓             │
│  └─ Lint finish ✓                   │
│                                     │
│  Time 0-3 min:                      │
│  ├─ Integration tests finish ✓      │
│                                     │
│  Time 3-5 min:                      │
│  ├─ E2E tests run (only if prev ok) │
│  └─ E2E finish ✓                    │
│                                     │
│  Time 5: All tests pass!            │
│                                     │
└─────────────────────────────────────┘

Key: Fast feedback (pass/fail in 5 minutes)
```

---

## 7. Rollback & Disaster Recovery

### What Happens If Deployment Fails?

```
Scenario: New version has critical bug

Automatic Rollback (Built-in):
  ├─ Health check fails
  ├─ After 30 seconds, detect failure
  ├─ Automatically switch to previous version
  └─ Bug fixed! (users don't notice)

Manual Rollback (If needed):
  ├─ Identify bad version
  ├─ One command: kubectl rollout undo
  ├─ Previous version re-deployed
  └─ Full rollback in < 1 minute
```

### Disaster Recovery Pattern
```
Critical failure in production?

Step 1: Immediate Action
  ├─ Switch traffic to backup region (5 sec)
  ├─ Page on-call engineer
  └─ Start incident response

Step 2: Diagnosis
  ├─ Check logs
  ├─ Identify root cause
  └─ Estimate impact

Step 3: Recovery
  ├─ Rollback code OR
  ├─ Fix database OR
  ├─ Scale resources
  └─ Verify health

Step 4: Prevention
  ├─ Add test to prevent future
  ├─ Update monitoring
  └─ Document incident

Total Time: < 15 minutes
Impact: Minimal (backup region active)
```

---

## 8. Monitoring & Observability Pattern

### What to Monitor
```
Application Level:
  ├─ Request rate (req/sec)
  ├─ Response time (latency)
  ├─ Error rate (5xx errors)
  ├─ Business metrics (conversions, revenue)
  └─ User experience (page load time)

Infrastructure Level:
  ├─ CPU usage
  ├─ Memory usage
  ├─ Disk usage
  ├─ Network throughput
  └─ Container restarts

Database Level:
  ├─ Query latency
  ├─ Connection count
  ├─ Slow queries
  └─ Replication lag

Deployment Level:
  ├─ Deployment frequency
  ├─ Deployment success rate
  ├─ Time to recovery (if failure)
  └─ Lead time for changes
```

### Monitoring Architecture
```
┌────────────────────────────────────────┐
│     Application Running                │
│     (Logs + Metrics)                   │
└────────────────────┬───────────────────┘
                     │
         ┌───────────┴──────────┐
         │                      │
         ▼                      ▼
   ┌──────────┐         ┌──────────────┐
   │ Logs     │         │ Metrics      │
   └──────┬───┘         └──────┬───────┘
          │                    │
          ├──→ CloudWatch ←────┤
          │
          ├─→ Parsing ←─────────┐
          │                      │
    ┌─────┴──────────────────────┴──────┐
    │   Aggregation & Analysis          │
    │   (Find patterns, anomalies)      │
    └─────┬──────────────────────────────┘
          │
     ┌────┴─────┬──────────────┬────────┐
     │           │              │        │
     ▼           ▼              ▼        ▼
 Dashboard   Alerts        Trends     Reports
     │           │              │        │
 (Pretty)   (PagerDuty)   (Patterns) (Weekly)
     │           │              │        │
     └─→ Human Decisions ←──────┴────────┘
```

---

## 9. Cost Optimization Patterns

### Lambda: Pay per execution
```
Pros: No waste (zero when idle)
Cons: Cold starts (slow first request)

Optimization:
├─ Keep functions warm (periodic invocation)
├─ Optimize package size (smaller = faster cold start)
├─ Use provisioned concurrency (guarantee warm)
└─ Monitor cost (CloudWatch billing)
```

### ECS: Pay per container hour
```
Pros: Predictable cost
Cons: Pay even if idle

Optimization:
├─ Right-size instances (not oversized)
├─ Use spot instances (75% discount, but interruptible)
├─ Auto-scale down at night
├─ Reserve instances for baseline (40% discount)
└─ Monitor utilization (CloudWatch)
```

### Kubernetes: Most expensive
```
Pros: Ultimate scalability
Cons: Complex, expensive infrastructure

Optimization:
├─ Use pod autoscaling (scale based on CPU)
├─ Use cluster autoscaling (add/remove nodes)
├─ Use spot instances for non-critical workloads
├─ Resource quotas (prevent runaway)
└─ Monitor costs (Kubecost or similar)
```

---

## 10. Security Patterns in CI/CD

### Defense in Depth
```
Layer 1: Code Review
  ├─ PR review required
  ├─ Automated checks block bad code
  └─ No direct push to main

Layer 2: Automated Testing
  ├─ Unit tests catch bugs
  ├─ Security scanning (SAST)
  └─ Dependency scanning

Layer 3: Container Security
  ├─ Image scanning for vulnerabilities
  ├─ Minimal base images (smaller attack surface)
  ├─ Run as non-root user
  └─ Sign images (verify authenticity)

Layer 4: Runtime Security
  ├─ Network policies (restrict traffic)
  ├─ RBAC (least privilege)
  ├─ Secrets management (never hardcode)
  └─ Audit logging (who did what)

Layer 5: Infrastructure
  ├─ VPC (isolated network)
  ├─ Security groups (firewall rules)
  ├─ WAF (web application firewall)
  └─ DDoS protection

Layer 6: Monitoring
  ├─ Log aggregation
  ├─ Anomaly detection
  ├─ Incident response
  └─ Post-mortems
```

---

## 11. Maturity Model: Your DevOps Journey

### Level 0: Manual (Starting)
```
Workflow:
├─ Code on laptop
├─ Manual tests
├─ Manual deployment
└─ Hope it works!

Pain: Slow, error-prone, stressful
Time to deploy: Days
Risk: High
```

### Level 1: Scripted (Early Automation)
```
Workflow:
├─ Code on GitHub
├─ One-click deploy script
├─ Manual testing before deployment
└─ Deployment takes 10 minutes

Pain: Still manual testing
Time to deploy: Hours
Risk: Medium
```

### Level 2: CI/CD (Automated)
```
Workflow:
├─ Code on GitHub
├─ Automated tests on every push
├─ Automated build & deploy
├─ Manual approval for production

Pain: Complex pipelines
Time to deploy: 10 minutes
Risk: Low
```

### Level 3: Continuous Deployment (Full Auto)
```
Workflow:
├─ Code on GitHub
├─ Automated tests
├─ Automated deployment everywhere
├─ Zero manual steps

Pain: Need excellent testing
Time to deploy: 1 minute
Risk: Very low (if tests good)
```

### Level 4: Observability & Recovery (Advanced)
```
Workflow:
├─ All of Level 3
├─ Comprehensive monitoring
├─ Auto-remediation
├─ Predictive scaling
└─ Chaos engineering

Pain: Complex, expensive
Time to detect issues: Seconds
Risk: Minimized
```

---

## 12. Putting It All Together: Complete Flow

### Developer's Day in an Advanced System
```
9:00 AM
  └─ Write code to add new feature
  └─ Local testing: ✓ works

9:15 AM
  └─ git commit + git push

9:16 AM
  └─ GitHub Actions starts
     ├─ Lint (PASS)
     ├─ Unit tests (PASS)
     ├─ Integration tests (PASS)
     ├─ Build Docker image (PASS)
     ├─ Deploy to staging (PASS)
     └─ Smoke tests (PASS)

9:22 AM
  └─ Slack notification: "✓ Ready for production"

9:30 AM
  └─ Developer approves deployment
  └─ GitHub Actions continues
     ├─ Deploy to production (canary: 5%)
     ├─ Monitor (1 minute)
     ├─ Increase to 50% (if good)
     ├─ Monitor (1 minute)
     └─ Increase to 100%

9:35 AM
  └─ Feature live!
  └─ CloudWatch monitoring starts
     ├─ Error rate: 0%
     ├─ Response time: 45ms
     └─ All good! ✓

10:00 AM
  └─ User sees new feature
  └─ Happiness! 😊

Key Outcome:
├─ Deployed in 20 minutes total
├─ Automated quality gates
├─ Gradual rollout (safe)
├─ Continuous monitoring
└─ Low risk, high confidence
```

---

## Summary: System Design Thinking

```
PRINCIPLE 1: Automate Everything
  ├─ Testing
  ├─ Building
  ├─ Deployment
  └─ Monitoring

PRINCIPLE 2: Measure Everything
  ├─ Build time
  ├─ Test results
  ├─ Deployment frequency
  ├─ Error rates
  └─ Business metrics

PRINCIPLE 3: Fail Safely
  ├─ Automated rollback
  ├─ Feature flags
  ├─ Canary deployments
  └─ Backup systems

PRINCIPLE 4: Continuous Learning
  ├─ Incident post-mortems
  ├─ Metrics dashboards
  ├─ Team retrospectives
  └─ Industry trends

RESULT: Production deployments
with confidence, speed, and safety!
```

---

## Next Steps

- Read DOCKER_INTERNALS.md (understand containers)
- Read CICD_MENTAL_MODELS.md (understand automation)
- Review the specific workflows in `../workflows/`
- Implement Tier 1 (Lambda) first
- Advance to Tier 2 (ECS) as complexity grows
