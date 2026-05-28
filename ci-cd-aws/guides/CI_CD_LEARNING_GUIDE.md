# 🚀 GitHub Actions & AWS CI/CD Learning Guide

Welcome! This guide will help you understand and implement CI/CD pipelines with GitHub Actions and deploy to AWS.

---

## 📋 What We've Created

### Python Application
- **`hello_world_app.py`** - Simple app with calculator functionality
- **`test_hello_world_app.py`** - Unit tests
- **`ai_agent_example.py`** - Claude AI agent for advanced workflows

### GitHub Actions Workflows
```
.github/workflows/
├── hello-world-ci.yml        # Build & test on multiple Python versions
├── docker-build.yml          # Build and test Docker images
└── deploy-ai-agent.yml       # Deploy AI agent to AWS Lambda
```

### Docker
- **`Dockerfile`** - Containerize the Python app

### Supporting Files
- **`memory/ci-cd-guide.md`** - Detailed learning materials (in this directory)

---

## 🎯 Quick Start (5 minutes)

### 1. View the Basic Workflow
Open `.github/workflows/hello-world-ci.yml` and read the comments. This is your simplest CI/CD pipeline.

**What it does**:
- Runs on every push to `main` or `develop` branches
- Tests on Python 3.9, 3.10, and 3.11 (parallel jobs)
- Runs unit tests
- Performs code quality checks

### 2. Push to GitHub
```bash
git add .github/workflows/hello-world-ci.yml hello_world_app.py test_hello_world_app.py
git commit -m "Add GitHub Actions CI/CD pipeline"
git push origin main
```

### 3. Watch It Run
1. Go to your GitHub repo
2. Click **Actions** tab
3. Watch the workflow execute in real-time
4. Click on the workflow to see detailed logs

### 4. Download Results
1. Click the workflow run
2. Scroll to **Artifacts**
3. Download any generated files (test reports, Docker build info)

---

## 🔍 Understanding Each Workflow

### Workflow 1: `hello-world-ci.yml` (BASIC)

**Purpose**: Run tests on every push

**Key Concepts**:
```yaml
on:
  push:                          # Trigger on push
    branches: [main, develop]    # Only to these branches
  workflow_dispatch:             # Manual trigger via UI
```

**Jobs**:
1. **build** - Runs tests on multiple Python versions
2. **quality** - Checks code style
3. **summary** - Final status report

**What You Learn**:
- Workflow structure (on, jobs, steps)
- Matrix strategy (multiple versions)
- Job dependencies (`needs:`)
- Conditional execution (`if:`)

**Try This**:
- Edit `hello_world_app.py` and break something
- Push to GitHub
- Watch the workflow fail
- Fix it and push again
- See the workflow pass

---

### Workflow 2: `docker-build.yml` (INTERMEDIATE)

**Purpose**: Build and test Docker images

**Key Concepts**:
```yaml
docker build -t hello-world-app:latest .
docker run --rm hello-world-app:latest
```

**What You Learn**:
- Docker image building
- Container testing
- Artifact uploads
- Docker Hub integration (optional)

**Try This**:
1. Build locally first:
   ```bash
   docker build -t hello-world-app:latest .
   docker run --rm hello-world-app:latest
   ```

2. Push to GitHub and let the workflow build it
3. Compare local and GitHub Actions builds

**Next Level**:
- Push to Docker Hub (requires account + secrets)
- Deploy to AWS ECR (Elastic Container Registry)

---

### Workflow 3: `deploy-ai-agent.yml` (ADVANCED)

**Purpose**: Deploy Claude AI agent to AWS Lambda

**Key Concepts**:
```yaml
- Copy agent code
- Install dependencies (anthropic SDK)
- Create Lambda deployment package
- Upload to AWS
```

**What You Learn**:
- Packaging Python apps for serverless
- AWS Lambda deployment
- Environment variables in GitHub Secrets
- Conditional deployment (`if: false` to disable)

**Try This**:

Step 1: Run agent locally (requires API key)
```bash
export ANTHROPIC_API_KEY="your-key-here"
python ai_agent_example.py
```

Step 2: Package for Lambda
```bash
mkdir lambda-package
cp ai_agent_example.py lambda-package/
pip install anthropic -t lambda-package/
cd lambda-package && zip -r ../agent.zip . && cd ..
```

Step 3: Deploy via GitHub Actions
- Create AWS Lambda function: `my-ai-agent`
- Set environment variable: `ANTHROPIC_API_KEY`
- Add AWS secrets to GitHub (see below)
- Change `if: false` to `if: true` in workflow
- Push to main branch

---

## 🔐 Setting Up Secrets (Critical!)

### GitHub Secrets Setup

**For Docker Hub** (optional):
1. Go to repo → Settings → Secrets and variables → Actions
2. Click "New repository secret"
3. Add:
   - `DOCKER_USERNAME` - Your Docker Hub username
   - `DOCKER_PASSWORD` - Docker Hub access token

**For AWS Lambda** (required for agent deployment):
1. Create AWS IAM user with Lambda permissions
2. Generate access key + secret key
3. Add to GitHub:
   - `AWS_ACCESS_KEY_ID`
   - `AWS_SECRET_ACCESS_KEY`

⚠️ **NEVER commit secrets!** Use GitHub Secrets instead.

---

## 📚 Learning Roadmap

### Level 1: Understand Workflows (Day 1)
- [ ] Read `.github/workflows/hello-world-ci.yml`
- [ ] Understand triggers (`on:`)
- [ ] Understand jobs and steps
- [ ] Run workflow and view logs
- [ ] Download artifacts

**Duration**: 1 hour

**Concepts**: Triggers, jobs, steps, runners

---

### Level 2: Add Tests & Quality Checks (Day 2)
- [ ] Write unit tests (see `test_hello_world_app.py`)
- [ ] Run tests locally
- [ ] Add pytest to workflow
- [ ] Add linting (flake8)
- [ ] View code coverage reports

**Duration**: 2 hours

**Concepts**: Testing strategy, code quality, coverage

---

### Level 3: Containerization (Day 3)
- [ ] Write Dockerfile
- [ ] Build image locally
- [ ] Test container locally
- [ ] Add Docker build to workflow
- [ ] Push to Docker Hub (optional)

**Duration**: 2 hours

**Concepts**: Docker, containers, registry, artifacts

---

### Level 4: AWS Deployment (Day 4)
- [ ] Create AWS account (free tier)
- [ ] Create Lambda function
- [ ] Create IAM user for CI/CD
- [ ] Add AWS secrets to GitHub
- [ ] Deploy Lambda from GitHub Actions
- [ ] Invoke Lambda function

**Duration**: 3 hours

**Concepts**: AWS Lambda, IAM, serverless, infrastructure

---

### Level 5: AI Agents (Day 5)
- [ ] Understand `ai_agent_example.py`
- [ ] Run agent locally with Anthropic API key
- [ ] Package agent for Lambda
- [ ] Deploy agent to Lambda
- [ ] Invoke agent from command line
- [ ] Create agent for your own use case

**Duration**: 3 hours

**Concepts**: AI integration, API keys, serverless agents

---

## 🛠️ Common Tasks

### Task 1: Run Workflow Manually
```
Go to Actions → select workflow → click "Run workflow" button
```

### Task 2: View Detailed Logs
```
1. Go to Actions
2. Click workflow run
3. Click job name
4. Click step to expand
5. View output
```

### Task 3: Debug Failed Workflow
```yaml
- name: Debug Info
  if: failure()  # Only run if previous step failed
  run: |
    echo "Debug info here"
    python --version
    pip list
```

### Task 4: Skip Workflow for Certain Commits
```bash
git commit -m "Update README [skip ci]"
# The [skip ci] text prevents workflow from running
```

### Task 5: Deploy Only on Version Tags
```yaml
on:
  push:
    tags:
      - 'v*'  # Run when pushing tags like v1.0.0
```

---

## 🔗 Comparing with Azure Pipelines (Your Experience)

| Task | Azure | GitHub Actions |
|------|-------|----------------|
| Config file | `azure-pipelines.yml` | `.github/workflows/*.yml` |
| Triggers | `trigger:` | `on:` |
| Manual trigger | UI button | `workflow_dispatch:` |
| Jobs | Same | Same |
| Matrix | `matrix:` | `strategy: matrix:` |
| Artifacts | `publishBuildArtifacts@1` | `actions/upload-artifact@v3` |
| Variables | Variable groups | Secrets |
| Environments | Deployments | Environments |

**Key Difference**: GitHub Actions is simpler for basic workflows!

---

## 📖 Detailed Resources

For in-depth learning, see:
- **`memory/ci-cd-guide.md`** - Comprehensive learning guide in this directory
  - Full YAML syntax explained
  - Docker best practices
  - AWS deployment strategies
  - AI agent deployment

---

## 🎓 Real-World Example: Full Pipeline

Here's what a production pipeline might look like:

```yaml
name: Production Pipeline

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

jobs:
  # Test phase
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-python@v4
        with:
          python-version: '3.11'
      - run: pip install -r requirements.txt
      - run: pytest --cov
      - run: flake8 .

  # Build phase
  build:
    needs: test  # Wait for tests to pass
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - run: docker build -t myapp:${{ github.sha }} .

  # Deploy phase
  deploy:
    needs: build  # Wait for build to succeed
    if: github.event_name == 'push'  # Only on push, not PR
    runs-on: ubuntu-latest
    steps:
      - name: Deploy to AWS
        run: aws lambda update-function-code --function-name myapp --zip-file fileb://package.zip
        env:
          AWS_ACCESS_KEY_ID: ${{ secrets.AWS_ACCESS_KEY_ID }}
          AWS_SECRET_ACCESS_KEY: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
```

**Flow**: Test → Build → Deploy (only on main)

---

## ✅ Checklist: Getting Started

- [ ] Create GitHub repo (or use existing)
- [ ] Clone repo locally
- [ ] Copy `hello_world_app.py` and `test_hello_world_app.py`
- [ ] Create `.github/workflows/` directory
- [ ] Copy `.github/workflows/hello-world-ci.yml`
- [ ] Push to GitHub
- [ ] Watch Actions tab for workflow
- [ ] View logs and artifacts
- [ ] Read through `.github/workflows/hello-world-ci.yml` with comments
- [ ] Proceed to Level 2 (testing)

---

## 🚀 Next Steps

1. **Immediate**: Run the basic workflow and understand it
2. **Tomorrow**: Add Docker and push images
3. **This Week**: Deploy to AWS Lambda
4. **Soon**: Deploy AI agents to production

---

## 💡 Tips & Tricks

1. **Use `run: |` for multi-line commands**
   ```yaml
   - run: |
       echo "Line 1"
       echo "Line 2"
   ```

2. **Reuse workflows across repos**
   - Create `.github/workflows/` in a separate repo
   - Reference from other repos: `uses: org/repo/.github/workflows/test.yml@main`

3. **Cache dependencies to speed up builds**
   ```yaml
   - uses: actions/cache@v3
     with:
       path: ~/.cache/pip
       key: ${{ runner.os }}-pip-${{ hashFiles('**/requirements.txt') }}
   ```

4. **Run workflow on schedule**
   ```yaml
   on:
     schedule:
       - cron: '0 2 * * *'  # Daily at 2 AM UTC
   ```

5. **Protect main branch**
   - Settings → Branches → Add rule for `main`
   - Require status checks (workflows) to pass before merge
   - Require pull request reviews

---

## ❓ Troubleshooting

**Workflow won't run**
- Check branch name matches `on: push: branches:`
- Check file syntax in `.github/workflows/*.yml`
- Use YAML validator: https://www.yamllint.com/

**Tests fail locally but pass in CI**
- Different Python version? Check matrix in workflow
- Different dependencies? Update requirements.txt

**Docker build fails**
- Run locally first: `docker build -t myapp .`
- Check Dockerfile syntax
- Verify all files exist

**AWS deployment fails**
- Check IAM user has Lambda permissions
- Verify `ANTHROPIC_API_KEY` is set in Lambda environment
- Check function name matches in workflow

---

## 📞 Getting Help

1. **GitHub Actions Docs**: https://docs.github.com/en/actions
2. **Docker Docs**: https://docs.docker.com/
3. **AWS Lambda Docs**: https://docs.aws.amazon.com/lambda/
4. **Anthropic API Docs**: https://docs.anthropic.com/

---

## 🎉 You're Ready!

You now have:
- ✅ A working GitHub Actions pipeline
- ✅ Python app with tests
- ✅ Docker containerization
- ✅ AI agent example
- ✅ AWS deployment workflow

**Start with Level 1 and work your way up. Good luck! 🚀**
