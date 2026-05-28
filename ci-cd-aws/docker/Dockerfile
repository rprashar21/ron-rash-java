# Multi-stage Dockerfile for Hello World App
# Stage 1: Builder
FROM python:3.11-slim as builder

WORKDIR /build

# Copy requirements file (if you create one)
# COPY requirements.txt .
# RUN pip install --no-cache-dir -r requirements.txt

# Stage 2: Runtime
FROM python:3.11-slim

WORKDIR /app

# Copy app from builder stage
COPY hello_world_app.py .
COPY test_hello_world_app.py .

# Run the app by default
CMD ["python", "hello_world_app.py"]

# Labels for metadata
LABEL maintainer="your-email@example.com"
LABEL description="Hello World App for CI/CD Learning"
LABEL version="1.0"
