# AWS Deployment Guide for CI/CD Pipelines

This guide shows you how to deploy from GitHub Actions to AWS.

---

## 📋 Prerequisites

- [ ] AWS account (free tier available)
- [ ] GitHub account with repo
- [ ] AWS CLI installed locally (optional but helpful)
- [ ] Anthropic API key (for AI agents)

---

## 🏗️ Architecture Overview

```
Your Local Machine
        ↓
  Push to GitHub
        ↓
  GitHub Actions (CI/CD)
        ↓
  Build & Test
        ↓
  Deploy to AWS
        ↓
  Running in AWS (Lambda / EC2 / ECS)
```

---

## ⚡ Option 1: Deploy to AWS Lambda (EASIEST)

Lambda is perfect for:
- APIs that don't need to run constantly
- Event-triggered functions
- Microservices
- AI agent endpoints
- Cost-effective (pay per execution)

### Step 1: Create Lambda Function

**Via AWS Console**:
1. Go to AWS Lambda → **Create function**
2. Configure:
   - **Name**: `my-hello-app`
   - **Runtime**: Python 3.11
   - **Architecture**: x86_64

3. Click **Create function**

### Step 2: Create IAM User for GitHub

Why? GitHub Actions needs permission to update your Lambda function.

**Via AWS IAM Console**:
1. Go to IAM → **Users** → **Create user**
2. **User name**: `github-ci-user`
3. Click **Next**

4. On Permissions page:
   - Click **Attach policies directly**
   - Search: `AWSLambdaFullAccess`
   - Check the box
   - Click **Next** → **Create user**

### Step 3: Generate Access Keys

1. Click the user you just created: `github-ci-user`
2. Go to **Security credentials** tab
3. Click **Create access key**
4. Choose **Application running outside AWS**
5. Click **Next**
6. Copy the **Access Key ID** and **Secret Access Key**
   - ⚠️ **SAVE THESE!** You won't see the secret again

### Step 4: Add Secrets to GitHub

1. Go to your GitHub repo → **Settings**
2. **Secrets and variables** → **Actions**
3. Click **New repository secret**
4. Add:
   - **Name**: `AWS_ACCESS_KEY_ID`
   - **Value**: (paste from Step 3)
   - Click **Add secret**

5. Repeat for:
   - **Name**: `AWS_SECRET_ACCESS_KEY`
   - **Value**: (paste from Step 3)

### Step 5: Deploy from GitHub Actions

Edit `.github/workflows/deploy-ai-agent.yml`:

```yaml
- name: Deploy to AWS Lambda
  if: true  # Change from 'false' to 'true'
  run: |
    aws lambda update-function-code \
      --function-name my-hello-app \
      --zip-file fileb://agent-deployment.zip
  env:
    AWS_ACCESS_KEY_ID: ${{ secrets.AWS_ACCESS_KEY_ID }}
    AWS_SECRET_ACCESS_KEY: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
    AWS_DEFAULT_REGION: us-east-1
```

### Step 6: Test the Deployment

1. Push to main branch:
   ```bash
   git add .
   git commit -m "Enable Lambda deployment"
   git push origin main
   ```

2. Go to GitHub Actions and watch the workflow
3. Once complete, go to AWS Lambda console
4. You should see the code updated!

---

## 🔌 Option 2: Deploy to AWS EC2 (TRADITIONAL)

EC2 is perfect for:
- Traditional web apps
- Full server control
- Always-running services
- Monolithic applications

### Step 1: Launch EC2 Instance

**Via AWS Console**:
1. Go to EC2 → **Instances** → **Launch instance**
2. Configure:
   - **Name**: `my-app-server`
   - **AMI**: Ubuntu 24.04 LTS (free tier eligible)
   - **Instance type**: t2.micro (free tier)
   - **Key pair**: Create new → Download `.pem` file
   - **Security group**: Allow SSH (port 22) and HTTP (port 80)

3. Click **Launch instance**

### Step 2: Get Server Details

1. Go to Instances
2. Click your instance
3. Note the **Public IPv4 address**: `54.123.456.789` (example)
4. Note the **Private key** file: `my-key.pem`

### Step 3: Add SSH Key to GitHub Secrets

1. Open the downloaded `.pem` file with a text editor
2. Copy the entire content
3. Go to GitHub → Settings → Secrets
4. Add secret:
   - **Name**: `EC2_PRIVATE_KEY`
   - **Value**: (paste entire .pem file content)

5. Add another secret:
   - **Name**: `EC2_HOST`
   - **Value**: `54.123.456.789` (your IP)

### Step 4: Create Deployment Workflow

**File**: `.github/workflows/deploy-to-ec2.yml`

```yaml
name: Deploy to EC2

on:
  push:
    branches: [main]

jobs:
  deploy:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Deploy to EC2
        run: |
          mkdir -p ~/.ssh
          echo "${{ secrets.EC2_PRIVATE_KEY }}" > ~/.ssh/id_rsa
          chmod 600 ~/.ssh/id_rsa
          ssh-keyscan -H ${{ secrets.EC2_HOST }} >> ~/.ssh/known_hosts

          # Copy files to server
          scp -i ~/.ssh/id_rsa -r . ubuntu@${{ secrets.EC2_HOST }}:/app

          # Run deployment script
          ssh -i ~/.ssh/id_rsa ubuntu@${{ secrets.EC2_HOST }} << 'EOF'
          cd /app
          python -m pip install --upgrade pip
          python -m pip install -r requirements.txt
          pkill -f "python hello_world_app.py"
          python hello_world_app.py &
          EOF
```

---

## 🐳 Option 3: Deploy to AWS ECS (CONTAINERS)

ECS is perfect for:
- Docker containers
- Microservices
- Auto-scaling
- Load balancing

### Step 1: Create ECR Repository

**Via AWS Console**:
1. Go to ECR → **Repositories** → **Create repository**
2. **Repository name**: `hello-app`
3. Click **Create repository**

### Step 2: Create ECS Cluster

1. Go to ECS → **Clusters** → **Create cluster**
2. Configure:
   - **Cluster name**: `my-cluster`
   - **Infrastructure**: EC2 instances (or Fargate for serverless)
3. Click **Create**

### Step 3: Create ECS Task Definition

1. Go to **Task Definitions** → **Create new task definition**
2. Configure:
   - **Task definition family**: `hello-app-task`
   - **Container name**: `hello-app`
   - **Image**: (you'll set this in workflow)
   - **Memory**: 512 MB
   - **CPU**: 256

### Step 4: Create Deployment Workflow

```yaml
name: Deploy to ECS

on:
  push:
    branches: [main]

jobs:
  deploy:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Build and push Docker image
        run: |
          aws ecr get-login-password --region us-east-1 | \
            docker login --username AWS --password-stdin ${{ secrets.AWS_ACCOUNT_ID }}.dkr.ecr.us-east-1.amazonaws.com

          docker build -t hello-app:latest .
          docker tag hello-app:latest ${{ secrets.AWS_ACCOUNT_ID }}.dkr.ecr.us-east-1.amazonaws.com/hello-app:latest
          docker push ${{ secrets.AWS_ACCOUNT_ID }}.dkr.ecr.us-east-1.amazonaws.com/hello-app:latest

      - name: Update ECS service
        run: |
          aws ecs update-service \
            --cluster my-cluster \
            --service hello-app-service \
            --force-new-deployment
        env:
          AWS_ACCESS_KEY_ID: ${{ secrets.AWS_ACCESS_KEY_ID }}
          AWS_SECRET_ACCESS_KEY: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
          AWS_DEFAULT_REGION: us-east-1
```

---

## 🤖 Deploying AI Agents: Detailed Steps

### Deploy Claude Agent to Lambda

**Architecture**:
```
GitHub Push
    ↓
Build Python package (with anthropic SDK)
    ↓
Create ZIP file
    ↓
Upload to Lambda
    ↓
Lambda has ANTHROPIC_API_KEY set
    ↓
Ready to process requests
```

### Step 1: Create Lambda Function for AI Agent

**Via AWS Console**:
1. Lambda → **Create function**
2. **Name**: `my-ai-agent`
3. **Runtime**: Python 3.11
4. Click **Create function**

### Step 2: Set Environment Variable

1. Click the function
2. Go to **Configuration** tab
3. **Environment variables** → **Edit**
4. Add:
   - **Key**: `ANTHROPIC_API_KEY`
   - **Value**: (your Anthropic API key)
5. Click **Save**

### Step 3: Create Lambda Handler

The workflow creates `lambda_handler.py` automatically. Here's what it does:

```python
import json
from ai_agent_example import SimpleAIAgent

def lambda_handler(event, context):
    """Handle Lambda invocation"""
    agent = SimpleAIAgent()

    task_type = event.get('task_type', 'explain')
    content = event.get('content')

    result = agent.explain_devops_concept(content)

    return {
        'statusCode': 200,
        'body': json.dumps({'result': result})
    }
```

### Step 4: Deploy via GitHub Actions

Edit `.github/workflows/deploy-ai-agent.yml`:

```yaml
- name: Deploy to AWS Lambda
  if: true  # Enable deployment
  run: |
    aws lambda update-function-code \
      --function-name my-ai-agent \
      --zip-file fileb://agent-deployment.zip
  env:
    AWS_ACCESS_KEY_ID: ${{ secrets.AWS_ACCESS_KEY_ID }}
    AWS_SECRET_ACCESS_KEY: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
    AWS_DEFAULT_REGION: us-east-1
```

### Step 5: Invoke the Agent

**Via AWS Console**:
1. Lambda → Click function
2. **Test** tab
3. Create test event:
   ```json
   {
     "task_type": "explain",
     "content": "CI/CD Pipeline"
   }
   ```
4. Click **Test**
5. View response from Claude!

**Via AWS CLI**:
```bash
aws lambda invoke \
  --function-name my-ai-agent \
  --payload '{"task_type":"explain","content":"CI/CD"}' \
  response.json

cat response.json
```

**Via curl** (if you create API Gateway):
```bash
curl -X POST https://api-endpoint.lambda-url.us-east-1.on.aws/ \
  -H "Content-Type: application/json" \
  -d '{"task_type":"explain","content":"CI/CD"}'
```

---

## 🔑 AWS Security Best Practices

### ✅ DO:
- [ ] Use IAM users (not root account)
- [ ] Limit permissions (least privilege)
- [ ] Rotate access keys regularly
- [ ] Use GitHub Secrets (don't commit credentials)
- [ ] Enable MFA on AWS account
- [ ] Monitor CloudWatch logs

### ❌ DON'T:
- [ ] Share access keys
- [ ] Use root account for CI/CD
- [ ] Commit `.pem` files
- [ ] Grant full AWS access
- [ ] Reuse access keys across repos

---

## 📊 Comparing AWS Services

| Service | Use Case | Cost | Complexity |
|---------|----------|------|-----------|
| **Lambda** | Serverless, APIs, events | Pay per execution | Low |
| **EC2** | Full control, servers | Per hour | Medium |
| **ECS** | Containers, microservices | Per task | Medium-High |
| **AppRunner** | Containers simplified | Per second | Low |

**Recommendation for Beginners**: Start with Lambda (easiest, cheapest)

---

## 🚨 Troubleshooting AWS Deployments

### Lambda Won't Update
```
Error: User is not authorized to perform: lambda:UpdateFunctionCode
```
**Fix**: Check IAM user has `AWSLambdaFullAccess` policy

### EC2 Connection Fails
```
Permission denied (publickey)
```
**Fix**: Check `.pem` file permissions: `chmod 600 ~/.ssh/id_rsa`

### ECS Deployment Times Out
```
Error: Container failed to start
```
**Fix**: Check CloudWatch logs, verify image in ECR

### Agent Crashes with API Error
```
openai.error.AuthenticationError: No API key provided
```
**Fix**: Set `ANTHROPIC_API_KEY` in Lambda environment variables

---

## 📈 Scaling Your Deployment

### When to Scale
- [ ] Lambda: > 1,000 requests/min → Consider concurrency limits
- [ ] EC2: > 80% CPU → Add auto-scaling group
- [ ] ECS: > 70% memory → Increase task count

### How to Scale
1. **Lambda**: Increase memory (faster CPU)
2. **EC2**: Auto Scaling Groups
3. **ECS**: Task count in service definition

---

## 💡 Real-World Example: Production Deployment

```yaml
name: Production Deployment

on:
  push:
    branches: [main]
    tags: [v*]  # Only deploy on version tags

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - run: pytest --cov

  build:
    needs: test
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - run: docker build -t myapp:${{ github.ref }} .

  deploy-staging:
    needs: build
    runs-on: ubuntu-latest
    steps:
      - run: |
          aws lambda update-function-code \
            --function-name myapp-staging \
            --zip-file fileb://package.zip

  deploy-production:
    needs: deploy-staging
    if: startsWith(github.ref, 'refs/tags/v')  # Only on version tags
    runs-on: ubuntu-latest
    steps:
      - run: |
          aws lambda update-function-code \
            --function-name myapp-prod \
            --zip-file fileb://package.zip
```

**Flow**: Test → Build → Staging → Production (only on tags)

---

## 🎓 Next Learning Steps

1. **Immediate**: Deploy Hello World to Lambda (30 min)
2. **This Week**: Deploy AI agent to Lambda (1 hour)
3. **Next Week**: Try EC2 deployment (2 hours)
4. **Advanced**: Multi-region deployments, auto-scaling (3+ hours)

---

## 📚 Resources

- **AWS Lambda**: https://docs.aws.amazon.com/lambda/
- **AWS IAM**: https://docs.aws.amazon.com/iam/
- **AWS EC2**: https://docs.aws.amazon.com/ec2/
- **AWS ECS**: https://docs.aws.amazon.com/ecs/
- **AWS Free Tier**: https://aws.amazon.com/free/

---

## ✅ Deployment Checklist

- [ ] AWS account created
- [ ] Lambda function created
- [ ] IAM user created with Lambda permissions
- [ ] Access keys generated and saved
- [ ] GitHub Secrets configured
- [ ] Workflow file updated
- [ ] Deployment tested
- [ ] CloudWatch logs reviewed
- [ ] Agent tested end-to-end

**You're ready to deploy! 🚀**
