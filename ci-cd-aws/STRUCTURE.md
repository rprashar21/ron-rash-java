# 📁 ci-cd-aws Folder Structure

```
ci-cd-aws/
│
├── 📄 README.md                    ← Navigation & overview
├── 📄 STRUCTURE.md                 ← This file
├── 📄 .gitignore                   ← Security (don't commit secrets)
│
├── 📚 guides/                      (3 comprehensive learning guides)
│   ├── QUICK_START.md              (5 min: Overview + learning paths)
│   ├── CI_CD_LEARNING_GUIDE.md     (15 min: Main concepts + workflows)
│   └── AWS_DEPLOYMENT_GUIDE.md     (1-2 hr: AWS strategies + setup)
│
├── 🐍 src/                         (Python application & tests)
│   ├── hello_world_app.py          (Simple calculator app)
│   ├── test_hello_world_app.py     (Unit tests for the app)
│   ├── ai_agent_example.py         (Claude AI agent example)
│   └── requirements.txt            (Python dependencies: pytest, anthropic, etc)
│
├── 🐳 docker/                      (Container files)
│   └── Dockerfile                  (Recipe to build Docker image)
│
└── ⚙️ workflows/                   (GitHub Actions CI/CD pipelines)
    ├── hello-world-ci.yml          (BASIC: Test on Python 3.9, 3.10, 3.11)
    ├── docker-build.yml            (INTERMEDIATE: Build & test Docker)
    └── deploy-ai-agent.yml         (ADVANCED: Deploy to AWS Lambda)
```

---

## 📊 File Count & Sizes

```
Total Files: 13
├── Guides: 3 markdown files (~35 KB)
├── Python Code: 4 files (~15 KB)
├── Docker: 1 file (~0.5 KB)
├── Workflows: 3 YAML files (~8 KB)
└── Config: 2 files
```

---

## 🎯 What Each Folder Does

### `guides/` - Learning Materials
- **QUICK_START.md** - Read this first (5 minutes)
- **CI_CD_LEARNING_GUIDE.md** - Comprehensive guide with examples
- **AWS_DEPLOYMENT_GUIDE.md** - Step-by-step AWS deployment

### `src/` - Python Application
- **hello_world_app.py** - Example app (run with `python hello_world_app.py`)
- **test_hello_world_app.py** - Tests (run with `python -m unittest test_hello_world_app.py -v`)
- **ai_agent_example.py** - Claude AI agent
- **requirements.txt** - Install with `pip install -r requirements.txt`

### `docker/` - Containerization
- **Dockerfile** - Build with `docker build -t hello-app .`
- Run with `docker run hello-app`

### `workflows/` - GitHub Actions Pipelines
- **hello-world-ci.yml** - Basic CI (test on push)
- **docker-build.yml** - Build Docker images
- **deploy-ai-agent.yml** - Deploy to AWS Lambda

Copy to `.github/workflows/` if using in GitHub repo:
```bash
mkdir -p .github/workflows
cp workflows/*.yml .github/workflows/
```

---

## 🚀 Where to Start

1. **First**: Read `guides/QUICK_START.md`
2. **Then**: Pick a learning path from QUICK_START.md
3. **Next**: Run code locally from `src/`
4. **Advanced**: Deploy using workflows and AWS

---

## 📋 File Details

### Python Files
- `hello_world_app.py` (52 lines) - Classes, functions, examples
- `test_hello_world_app.py` (60 lines) - 8 test cases
- `ai_agent_example.py` (140 lines) - Claude API integration
- `requirements.txt` (11 lines) - anthropic, pytest, flake8, etc

### Workflow Files
- `hello-world-ci.yml` (60 lines) - Tests on 3 Python versions
- `docker-build.yml` (45 lines) - Build & test Docker image
- `deploy-ai-agent.yml` (70 lines) - Deploy to AWS Lambda

### Guide Files
- `QUICK_START.md` (400 lines) - Overview + 4 learning paths
- `CI_CD_LEARNING_GUIDE.md` (600 lines) - 10 comprehensive sections
- `AWS_DEPLOYMENT_GUIDE.md` (500 lines) - 3 AWS deployment strategies

---

## ✅ Quick Start

```bash
# 1. View the structure
cd ci-cd-aws
ls -la

# 2. Read the guides
cat guides/QUICK_START.md

# 3. Run Python app
python src/hello_world_app.py

# 4. Run tests
python -m unittest src/test_hello_world_app.py -v

# 5. Build Docker
cd docker
docker build -t hello-app .
docker run hello-app
```

---

## 🎓 Learning Outcomes

After completing this learning system, you'll understand:

✅ GitHub Actions basics & triggers
✅ CI/CD pipelines & workflows
✅ Testing strategies
✅ Docker containerization
✅ AWS Lambda deployment
✅ Environment variables & secrets
✅ AI agent integration
✅ DevOps best practices

---

## 📚 Total Learning Content

- **3 comprehensive guides** (1500+ lines)
- **4 Python examples** with tests
- **3 GitHub Actions workflows** (increasing complexity)
- **1 Dockerfile** with best practices
- **All organized** in one folder

---

**Everything you need to learn CI/CD & AWS is here! 🚀**
