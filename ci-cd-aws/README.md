# 🚀 CI/CD & AWS Learning Hub

Complete GitHub Actions + AWS deployment learning system organized in one place.

---

## 📂 Folder Structure

```
ci-cd-aws/
├── guides/                          # 📚 Learning materials
│   ├── QUICK_START.md              (5 min: Start here!)
│   ├── CI_CD_LEARNING_GUIDE.md     (Comprehensive guide)
│   └── AWS_DEPLOYMENT_GUIDE.md     (AWS-specific guide)
│
├── src/                            # 🐍 Python source code
│   ├── hello_world_app.py          (Simple calculator app)
│   ├── test_hello_world_app.py     (Unit tests)
│   ├── ai_agent_example.py         (Claude AI agent)
│   └── requirements.txt            (Python dependencies)
│
├── docker/                         # 🐳 Docker files
│   └── Dockerfile                  (Container recipe)
│
├── workflows/                      # ⚙️ GitHub Actions pipelines
│   ├── hello-world-ci.yml          (Basic: Test on push)
│   ├── docker-build.yml            (Intermediate: Build Docker)
│   └── deploy-ai-agent.yml         (Advanced: Deploy to Lambda)
│
├── .gitignore                      # 🔒 Security settings
└── README.md                       # This file
```

---

## 🎯 Quick Navigation

### 📖 **I want to learn** (Pick one):
- **5 minutes** → `guides/QUICK_START.md`
- **15 minutes** → `guides/CI_CD_LEARNING_GUIDE.md`
- **Deep dive** → Both guides + `../memory/ci-cd-guide.md`

### 🚀 **I want to deploy**:
- **GitHub Actions** → `guides/CI_CD_LEARNING_GUIDE.md` (Section 1-3)
- **AWS Lambda** → `guides/AWS_DEPLOYMENT_GUIDE.md` (Section 1)
- **AWS EC2** → `guides/AWS_DEPLOYMENT_GUIDE.md` (Section 2)
- **AWS ECS** → `guides/AWS_DEPLOYMENT_GUIDE.md` (Section 3)

### 🤖 **I want AI agents**:
- **Understand** → `src/ai_agent_example.py` (read the code)
- **Run locally** → `guides/AWS_DEPLOYMENT_GUIDE.md` (Deploy section)
- **Deploy** → `workflows/deploy-ai-agent.yml`

---

## 🚀 Setup Instructions

### Step 1: Copy files to GitHub repo root (if needed)
```bash
# If you want workflows to run in GitHub Actions:
cp -r workflows/* ../../.github/workflows/

# If you want to run Python code:
cp -r src/* ../../
```

### Step 2: View in place
```bash
# Or just read guides from here
cd guides
cat QUICK_START.md

# Run Python code
cd ../src
python hello_world_app.py
python -m unittest test_hello_world_app.py -v
```

### Step 3: Build Docker
```bash
cd docker
docker build -t hello-app .
docker run hello-app
```

---

## 📋 Files Reference

### Guides
| File | Duration | Best For |
|------|----------|----------|
| `QUICK_START.md` | 5 min | Overview & learning paths |
| `CI_CD_LEARNING_GUIDE.md` | 15 min | Main concepts & workflows |
| `AWS_DEPLOYMENT_GUIDE.md` | 1-2 hr | AWS deployment strategies |

### Python Code
| File | Purpose | Run With |
|------|---------|----------|
| `hello_world_app.py` | Simple app example | `python hello_world_app.py` |
| `test_hello_world_app.py` | Unit tests | `python -m unittest test_hello_world_app.py` |
| `ai_agent_example.py` | Claude AI agent | `export ANTHROPIC_API_KEY=... && python ai_agent_example.py` |
| `requirements.txt` | Dependencies | `pip install -r requirements.txt` |

### Docker
| File | Purpose | Run With |
|------|---------|----------|
| `Dockerfile` | Container recipe | `docker build -t hello-app .` |

### Workflows
| File | Triggers | Complexity |
|------|----------|-----------|
| `hello-world-ci.yml` | On push, matrix testing | Basic |
| `docker-build.yml` | Docker build & test | Intermediate |
| `deploy-ai-agent.yml` | Deploy to Lambda | Advanced |

---

## 🎓 Learning Paths

### **Path A: Fast Track (30 min)**
1. Read: `guides/QUICK_START.md`
2. Run: `python src/hello_world_app.py`
3. Copy workflows to GitHub
4. Watch Actions tab

### **Path B: Comprehensive (2-3 hours)**
1. Read: `guides/QUICK_START.md`
2. Read: `guides/CI_CD_LEARNING_GUIDE.md`
3. Run tests locally
4. Build Docker image
5. Read detailed guide

### **Path C: AWS Deployment (5+ hours)**
1. Complete Path B
2. Read: `guides/AWS_DEPLOYMENT_GUIDE.md`
3. Create AWS Lambda function
4. Deploy from GitHub Actions
5. Test in AWS console

### **Path D: AI Agents (Full day)**
1. Complete Path C
2. Get Anthropic API key
3. Run: `python src/ai_agent_example.py`
4. Deploy agent to Lambda
5. Invoke and test

---

## 🔄 Moving Workflows to GitHub

If you want GitHub Actions to run these workflows:

```bash
# From repo root
mkdir -p .github/workflows
cp ci-cd-aws/workflows/*.yml .github/workflows/

# Then push to GitHub
git add .github/
git commit -m "Add CI/CD workflows"
git push origin main
```

Then watch in: GitHub Repo → **Actions** tab

---

## 🔐 Setting Up Secrets (Required for Deployment)

### For GitHub Actions:
1. Go to **GitHub Repo → Settings → Secrets and variables → Actions**
2. Add:
   - `AWS_ACCESS_KEY_ID`
   - `AWS_SECRET_ACCESS_KEY`
   - `DOCKER_USERNAME` (optional)
   - `DOCKER_PASSWORD` (optional)

### For Local Testing:
```bash
# Create .env file (don't commit!)
export ANTHROPIC_API_KEY="your-key-here"
export AWS_ACCESS_KEY_ID="your-key"
export AWS_SECRET_ACCESS_KEY="your-key"
```

---

## 🧪 Testing Everything

### Test Python App
```bash
cd src
python hello_world_app.py
```
Expected output: Hello world app with calculator results

### Test Unit Tests
```bash
cd src
python -m unittest test_hello_world_app.py -v
```
Expected output: `OK` (all tests pass)

### Test Docker
```bash
cd docker
docker build -t hello-app .
docker run hello-app
```
Expected output: Same as Python app

---

## 📚 Concept Map

```
GitHub Actions (CI)
├── Triggers (on: push, pull_request)
├── Jobs (parallel work units)
├── Steps (sequential commands)
├── Runners (ubuntu-latest, etc)
└── Artifacts (store outputs)

AWS Deployment (CD)
├── Lambda (serverless functions)
├── EC2 (virtual machines)
├── ECS (container orchestration)
└── IAM (permissions & security)

Docker (Containerization)
├── Build image
├── Run container locally
└── Push to registry
```

---

## 🆘 Troubleshooting

### Workflow won't run
- Check branch name in `on: push: branches:`
- Validate YAML: https://www.yamllint.com/

### Tests fail
- Run locally: `python -m unittest test_hello_world_app.py -v`
- Check Python version: `python --version`

### Docker fails
- Build locally first: `docker build -t hello-app .`
- Check Dockerfile syntax

### AWS deployment fails
- Verify IAM permissions
- Check secrets in GitHub Settings
- Review CloudWatch logs in AWS

---

## 🎯 Next Steps

1. **Immediate** (Now):
   - [ ] Read `guides/QUICK_START.md`
   - [ ] Pick a learning path

2. **Today** (1-2 hours):
   - [ ] Run Python app
   - [ ] Run tests
   - [ ] Read main guide

3. **This Week** (3-5 hours):
   - [ ] Build Docker image
   - [ ] Deploy to GitHub Actions
   - [ ] Deploy to AWS

4. **Advanced** (Next week+):
   - [ ] Deploy AI agents
   - [ ] Multi-region deployments
   - [ ] Auto-scaling & monitoring

---

## 📞 Help & Resources

**Learning GitHub Actions**: https://docs.github.com/en/actions
**Docker Docs**: https://docs.docker.com/
**AWS Lambda**: https://docs.aws.amazon.com/lambda/
**Anthropic API**: https://docs.anthropic.com/

---

## ✅ Checklist: Getting Started

- [ ] Read `guides/QUICK_START.md`
- [ ] Run `python src/hello_world_app.py`
- [ ] Run tests: `python -m unittest src/test_hello_world_app.py -v`
- [ ] Build Docker: `cd docker && docker build -t hello-app .`
- [ ] Copy workflows to `.github/workflows/` (if deploying)
- [ ] Read `guides/CI_CD_LEARNING_GUIDE.md`
- [ ] Decide on learning path

---

**Start with `guides/QUICK_START.md` → Happy learning! 🚀**
