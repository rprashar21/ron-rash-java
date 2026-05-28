# 🐳 Docker Internals: Mental Models & Deep Dive

Understand Docker at the architecture level, not just commands.

---

## 1. The Core Problem Docker Solves

### Without Docker (Traditional Development)
```
Developer's Machine         Production Server
┌─────────────────┐        ┌─────────────────┐
│ Python 3.11     │        │ Python 3.9      │ ❌ DIFFERENT VERSION!
│ PostgreSQL 12   │        │ PostgreSQL 14   │ ❌ DIFFERENT VERSION!
│ Redis 6.0       │        │ Redis 7.0       │ ❌ DIFFERENT VERSION!
│ App Code v1.0   │        │ App Code v1.0   │
│                 │        │                 │
│ "Works for me!" │        │ "Works for me!" │
└─────────────────┘        └─────────────────┘
         ✓                          ❌ BROKEN IN PROD!
```

**Problem**: "Works on my machine but not in production" 🤦

### With Docker (Containerized)
```
Developer's Machine         Production Server
┌─────────────────┐        ┌─────────────────┐
│  🐳 CONTAINER   │        │  🐳 CONTAINER   │
│ ┌─────────────┐ │        │ ┌─────────────┐ │
│ │ Python 3.11 │ │        │ │ Python 3.11 │ │
│ │PostgreSQL 12│ │        │ │PostgreSQL 12│ │
│ │ Redis 6.0   │ │        │ │ Redis 6.0   │ │
│ │ App Code    │ │        │ │ App Code    │ │
│ └─────────────┘ │        │ └─────────────┘ │
│                 │        │                 │
│ Exact Same!     │        │ Exact Same!     │
└─────────────────┘        └─────────────────┘
         ✓                          ✓ WORKS!
```

**Solution**: Docker ensures the same environment everywhere.

---

## 2. Docker Architecture: The Three-Layer Model

### Layer 1: Image (Blueprint)
```
Docker Image = Frozen snapshot of everything your app needs

┌─────────────────────────────────┐
│   DOCKER IMAGE (Read-only)      │
├─────────────────────────────────┤
│  Layer 1: Base OS (Ubuntu)      │  FROM ubuntu:20.04
├─────────────────────────────────┤
│  Layer 2: Python Installation   │  RUN apt-get install python3.11
├─────────────────────────────────┤
│  Layer 3: App Dependencies       │  RUN pip install flask
├─────────────────────────────────┤
│  Layer 4: Application Code       │  COPY app.py /app/
├─────────────────────────────────┤
│  Layer 5: Metadata               │  CMD ["python", "app.py"]
└─────────────────────────────────┘

File size: ~200MB (compressed)
Location: Docker registry (local or Docker Hub)
Used by: Stored, transported, shared
```

### Layer 2: Container (Running Instance)
```
Docker Container = Instance of an image (what actually runs)

┌──────────────────────────────────┐
│    DOCKER CONTAINER (Writable)   │
├──────────────────────────────────┤
│  Image Layers (read-only)        │  ← From image
├──────────────────────────────────┤
│  Container Layer (writable)      │  ← Changes made at runtime
└──────────────────────────────────┘

Process: PID 1 (your app)
Memory: Actually allocated (e.g., 512 MB)
Network: Virtual network interface
Filesystem: Isolated from host
Lifecycle: Can be stopped, restarted, deleted
```

### Layer 3: Registry (Storage & Distribution)
```
Docker Registry = Warehouse for images

┌──────────────────────────────────────┐
│      DOCKER REGISTRY                 │
├──────────────────────────────────────┤
│  Local Registry    Cloud Registry    │
│  ~/.docker/        Docker Hub        │
│  images/           (docker.io)       │
│                                      │
│  my-app:latest     my-app:v1.2.3    │
│  my-app:v1.0       my-app:v1.2.0    │
│                    (versioned)       │
└──────────────────────────────────────┘

Purpose: Store, version, and distribute images
Access: Pull (download) or push (upload)
```

---

## 3. Image Construction: How Layers Work

### The Dockerfile as a Recipe
```dockerfile
FROM python:3.11-slim              # Start with Python 3.11 image
WORKDIR /app                       # Set working directory
COPY requirements.txt .            # Copy file (creates new layer)
RUN pip install -r requirements.txt # Install packages (creates new layer)
COPY app.py .                      # Copy app code (creates new layer)
CMD ["python", "app.py"]           # Default command
```

### What Happens During `docker build`
```
Step 1: FROM python:3.11-slim
  ✓ Pull base image from Docker Hub if not cached
  Layer ID: sha256:abc123... (100 MB)

Step 2: WORKDIR /app
  ✓ Create directory /app in container filesystem
  Layer ID: sha256:def456... (minimal change)

Step 3: COPY requirements.txt .
  ✓ Copy local file into image
  Layer ID: sha256:ghi789... (1 KB)

Step 4: RUN pip install -r requirements.txt
  ✓ Execute pip install command
  ✓ Save result (new packages installed)
  Layer ID: sha256:jkl012... (50 MB - all packages)

Step 5: COPY app.py .
  ✓ Copy app into image
  Layer ID: sha256:mno345... (10 KB)

Step 6: CMD ["python", "app.py"]
  ✓ Set default command (metadata only)
  Layer ID: sha256:pqr678... (no new files)

Result: Image = Stack of all layers
Total Size: ~170 MB
Cached: Each layer cached so rebuilds are fast
```

### Why Layers Matter: Caching & Efficiency
```
Scenario: You rebuild the image after changing only app.py

Without Docker (Traditional):
1. Download Python
2. Install pip packages
3. Copy new app code
TIME: 2 minutes ❌

With Docker Layers (Smart Caching):
1. Use cached image up to "COPY app.py" ✓ instant
2. Copy new app.py (only 10 KB)
3. Complete
TIME: 5 seconds ✓

KEY INSIGHT: Docker layers are cached!
If nothing changed, that layer is reused.
```

### Layer Organization Best Practices
```dockerfile
# ❌ BAD: Large changes at the end (cache invalidation)
FROM ubuntu:20.04
RUN apt-get update && apt-get install -y python3 pip
RUN pip install -r requirements.txt
COPY . /app                    # App code invalidates cache
RUN some-build-command         # Has to rebuild from here on

# ✅ GOOD: Stable layers first, volatile layers last
FROM ubuntu:20.04
RUN apt-get update && apt-get install -y python3 pip
COPY requirements.txt /app/    # Stable (requirements rarely change)
RUN pip install -r requirements.txt
COPY . /app                    # Volatile (code changes frequently)
```

---

## 4. Container Lifecycle: What Actually Runs

### Inside a Container
```
┌─────────────────────────────────────────┐
│        CONTAINER RUNTIME ENVIRONMENT    │
├─────────────────────────────────────────┤
│                                         │
│  Process Tree:                          │
│  ├─ PID 1: python app.py (main)        │
│  ├─ PID 2: [child process if any]      │
│  └─ ...                                 │
│                                         │
│  Filesystem:                            │
│  /app/app.py (from image)               │
│  /tmp/ (writable, isolated)             │
│  /home/ (user can write)                │
│                                         │
│  Network:                               │
│  eth0 → Virtual network interface       │
│  IP: 172.17.0.2 (internal)             │
│                                         │
│  Memory:                                │
│  Allocated: 512 MB limit                │
│  Used: ~120 MB (app runtime)            │
│                                         │
│  Isolation:                             │
│  Kernel namespaces (PID, network, etc)  │
│  cgroups (resource limits)              │
│                                         │
└─────────────────────────────────────────┘
```

### Container Lifecycle
```
State 1: NOT CREATED
  docker build → Creates image

State 2: CREATED (but not running)
  docker create image-name → Creates container from image

State 3: RUNNING
  docker run image-name → Creates + starts container
  docker start container-id → Starts stopped container
  Process inside executes (PID 1)

State 4: PAUSED
  docker pause container-id → Freezes container
  (Process suspended, not stopped)

State 5: STOPPED
  docker stop container-id → Graceful shutdown
  Container layer still exists (can restart)
  Process terminates

State 6: DELETED
  docker rm container-id → Deletes container layer
  (Image still exists, can create new containers)
```

### What Happens When You Run a Container
```
$ docker run -it -p 8000:5000 -e API_KEY=secret my-app

1. Docker checks if image 'my-app' exists locally
   ├─ If not found: Pull from Docker Hub
   └─ If found: Use cached version

2. Docker creates a new container from the image
   ├─ Creates writable layer on top of image layers
   ├─ Sets up networking
   ├─ Allocates memory and CPU limits
   └─ Creates isolated filesystem

3. Docker configures the container
   ├─ Maps port 8000 (host) → 5000 (container)
   ├─ Sets environment variable API_KEY=secret
   └─ Sets stdin/stdout to terminal (-it flag)

4. Docker starts the container
   ├─ Executes PID 1: your app
   └─ Container is now RUNNING

5. Container executes until
   ├─ App finishes (natural exit)
   ├─ Error occurs (app crashes)
   └─ You stop it (Ctrl+C)

6. Container transitions to STOPPED
   ├─ All processes terminated
   ├─ Writable layer frozen
   └─ Can restart later with docker start
```

---

## 5. Image vs Container: The Analogy

```
CLASS (Image)              INSTANCE (Container)
─────────────────          ──────────────────
Blueprint                  Actual running copy
Read-only                  Writable (has state)
Stored on disk             Running in memory
Shared (many containers)   Individual (many per image)
Versioned (tags)           Short-lived (often)

ANALOGY:
Image = Recipe book
Container = Actual meal made from recipe

Image = Java class definition
Container = Object instance

Image = Docker Hub (static)
Container = Your running process (dynamic)

KEY INSIGHT:
One image can run as MANY containers simultaneously!
```

---

## 6. Docker Networking: How Containers Communicate

### Container Network Types
```
┌────────────────────────────────────────────────────────┐
│              HOST MACHINE (Your Computer)              │
├────────────────────────────────────────────────────────┤
│                                                        │
│  ┌──────────────────┐         ┌──────────────────┐    │
│  │   CONTAINER 1    │         │   CONTAINER 2    │    │
│  │  IP: 172.17.0.2  │────────│  IP: 172.17.0.3  │    │
│  │  Port: 5000      │  bridge │  Port: 5000      │    │
│  └──────────────────┘         └──────────────────┘    │
│         ↓                                               │
│    docker0 (virtual bridge network)                    │
│         ↓                                               │
│  eth0: 192.168.1.100 (host IP)                        │
│         ↓                                               │
│    PORT MAPPING: 8000:5000 (host:container)           │
│         ↓                                               │
│    localhost:8000 → Container:5000                     │
│                                                        │
└────────────────────────────────────────────────────────┘
```

### Network Modes Explained
```
1. BRIDGE (default)
   - Containers can talk to each other
   - Containers isolated from host network
   - Port mapping needed to access from host

   docker run -p 8000:5000 my-app
   Host 8000 → Container 5000

2. HOST
   - Container shares host's network
   - No port mapping needed
   - Less isolation but faster

   docker run --network=host my-app

3. NONE
   - No network connectivity
   - Used for isolated tasks only

   docker run --network=none my-app
```

---

## 7. Storage: Where Data Lives

### Container Storage Problem
```
You write files inside container:
├─ /app/data.txt (created at runtime)
├─ /tmp/cache.db (temporary data)
└─ /home/user/logs/ (application logs)

Container stops and is deleted:
$ docker rm container-id

Result: ALL DATA LOST ❌

Why? Container layer is deleted.
```

### Solutions: Volumes & Mounts
```
┌─────────────────────────────────────────┐
│          HOST MACHINE                   │
├─────────────────────────────────────────┤
│                                         │
│  Host Filesystem:                       │
│  /home/user/data/ ← Real files on host  │
│       ↑ (Volume Mount)                  │
│       ↓                                  │
│  ┌─────────────────────┐                │
│  │   CONTAINER         │                │
│  │  /app/data/  ↔ /home/user/data/     │
│  │  (same files!)                       │
│  └─────────────────────┘                │
│                                         │
└─────────────────────────────────────────┘

docker run -v /home/user/data:/app/data my-app

When container stops:
- Container is deleted
- But /home/user/data/ still exists ✓
- Data persists!
```

### Types of Storage
```
1. VOLUME (Preferred)
   - Docker manages the storage
   - Lost when container deleted
   - Good for: temporary data, caches

   docker run -v my-volume:/data my-app

2. BIND MOUNT (Host Path)
   - Maps host directory to container
   - Host owns the data
   - Data persists after container deleted
   - Good for: code development, persistent data

   docker run -v /host/path:/container/path my-app

3. TMPFS (RAM)
   - Store data in memory (RAM)
   - Lost when container stops
   - Good for: temporary files, performance

   docker run --tmpfs /tmp:rw,size=100m my-app
```

---

## 8. Multi-Container Systems: Docker Compose

### Problem: Single Container Isn't Enough
```
Your app needs:
├─ Web server (Python Flask)
├─ Database (PostgreSQL)
├─ Cache (Redis)
└─ Message Queue (RabbitMQ)

Running 4 separate docker run commands is tedious:
docker run -p 5000:5000 my-web-app
docker run -e POSTGRES_PASSWORD=secret postgres:12
docker run redis:6.0
docker run rabbitmq:latest
```

### Solution: Docker Compose
```yaml
version: '3.8'
services:
  web:
    image: my-web-app
    ports:
      - "5000:5000"
    environment:
      DATABASE_URL: postgresql://db:5432/myapp
    depends_on:
      - db

  db:
    image: postgres:12
    environment:
      POSTGRES_PASSWORD: secret
    volumes:
      - db-data:/var/lib/postgresql/data

  redis:
    image: redis:6.0
    ports:
      - "6379:6379"

volumes:
  db-data:

networks:
  default:
    name: my-network
```

### What Docker Compose Does
```
docker-compose up

1. Creates a network (my-network)
2. Starts DB container
3. Starts Redis container
4. Starts Web container
5. Connects them: web can reach "db:5432"

Result: All containers talk to each other!

docker-compose down
- Stops all containers
- Removes containers
- Network destroyed
- (Volumes preserved by default)
```

---

## 9. Image Registry: Distribution Model

### How Images Travel
```
Step 1: You build image locally
  docker build -t myapp:1.0 .
  └─ Image stored in ~/.docker/images/

Step 2: You tag for Docker Hub
  docker tag myapp:1.0 username/myapp:1.0
  └─ Prepare for upload

Step 3: You push to registry
  docker push username/myapp:1.0
  └─ Image uploaded to Docker Hub (or your registry)

Step 4: Someone pulls image
  docker pull username/myapp:1.0
  └─ Image downloaded, stored locally

Step 5: They run a container
  docker run username/myapp:1.0
  └─ Container created from image
```

### Registry Architecture
```
┌─────────────────────────────────────┐
│       DOCKER REGISTRY               │
│     (like GitHub for images)        │
├─────────────────────────────────────┤
│                                     │
│  Repositories:                      │
│  ├─ ubuntu                          │
│  │  ├─ Tag: latest (ubuntu:latest)  │
│  │  ├─ Tag: 20.04 (ubuntu:20.04)    │
│  │  └─ Tag: 22.04 (ubuntu:22.04)    │
│  │                                  │
│  ├─ python                          │
│  │  ├─ Tag: 3.11-slim               │
│  │  └─ Tag: 3.10-alpine             │
│  │                                  │
│  └─ myapp (your image)              │
│     ├─ Tag: latest                  │
│     ├─ Tag: v1.0                    │
│     └─ Tag: v1.1                    │
│                                     │
│  Metadata per image:                │
│  ├─ Image digest (hash)             │
│  ├─ Size                            │
│  ├─ Created date                    │
│  ├─ Layers (pull strategy)          │
│  └─ Platform (arm64, amd64)         │
│                                     │
└─────────────────────────────────────┘
```

---

## 10. Security: Container Isolation

### What's Isolated
```
✓ Filesystem: /app is separate per container
✓ Network: Each container has own IP/ports
✓ Processes: Can't see host processes (PID namespace)
✓ Users: Can't access host as root
✓ Memory: Limited (cgroups)
✓ CPU: Can be throttled

What's NOT isolated:
❌ Kernel: All containers share host kernel
❌ Underlying OS: Containers are not VMs
```

### Container vs VM
```
VIRTUAL MACHINES              CONTAINERS
├─ Full OS per VM            ├─ Shared kernel
├─ Slow startup (minutes)    ├─ Fast startup (seconds)
├─ Large (GBs)               ├─ Small (MBs)
├─ True isolation            ├─ Process-level isolation
├─ Can run different OS      ├─ Must match kernel
└─ Heavy resource usage      └─ Lightweight

Analogy:
VM = Separate house (own building)
Container = Apartment (shared building, isolated unit)
```

### Security Best Practices
```
1. Don't run as root
   FROM ubuntu
   RUN useradd -m appuser
   USER appuser
   CMD ["python", "app.py"]

2. Minimal base images
   FROM python:3.11-slim    ✓ Smaller attack surface
   FROM python:3.11         ❌ Includes unnecessary packages

3. No hardcoded secrets
   ❌ ENV DATABASE_PASSWORD=secret
   ✓ docker run -e DATABASE_PASSWORD=secret my-app

4. Image scanning
   docker scan my-app
   └─ Detects vulnerabilities

5. Resource limits
   docker run -m 512m --cpus 0.5 my-app
   └─ Memory: 512MB, CPU: 0.5 core
```

---

## 11. Mental Model: How It All Works Together

### The Complete Picture
```
┌──────────────────────────────────────────────────────────┐
│              YOU WRITE CODE                              │
│         (Python app + dependencies)                      │
└────────────────────┬─────────────────────────────────────┘
                     │
                     ▼
┌──────────────────────────────────────────────────────────┐
│         YOU WRITE DOCKERFILE                             │
│    (Recipe to build image)                               │
└────────────────────┬─────────────────────────────────────┘
                     │
        docker build (builds layers)
                     │
                     ▼
┌──────────────────────────────────────────────────────────┐
│         DOCKER IMAGE CREATED                             │
│   (Frozen, versioned, immutable)                         │
│   Stored: ~/.docker/images/ or Docker Hub                │
└────────────────────┬─────────────────────────────────────┘
                     │
        docker push (optional, share with team)
                     │
                     ▼
┌──────────────────────────────────────────────────────────┐
│         DOCKER REGISTRY                                  │
│    (Docker Hub, ECR, private registry)                   │
└────────────────────┬─────────────────────────────────────┘
                     │
    docker pull or docker run (fetches image if needed)
                     │
                     ▼
┌──────────────────────────────────────────────────────────┐
│         DOCKER CONTAINER CREATED                         │
│   (Running instance of image)                            │
│   Has: Writable layer, network, storage, process        │
└────────────────────┬─────────────────────────────────────┘
                     │
        docker start / docker stop / docker rm
                     │
                     ▼
┌──────────────────────────────────────────────────────────┐
│         APPLICATION RUNNING                              │
│    (Your code executes, processes incoming requests)     │
└──────────────────────────────────────────────────────────┘
```

### Key Insight: Immutability
```
Image = Immutable (never changes)
└─ my-app:1.0 always contains exactly the same code
└─ If you change code, create new image: my-app:1.1

Container = Mutable (has state)
└─ Writable layer on top of image
└─ Changes lost when container deleted

This is GOOD because:
✓ Reproducibility: Same image always behaves same way
✓ Versioning: Can easily rollback
✓ Testing: Easy to create isolated test environments
✓ Scaling: Can run same image 1000 times

This is WHY Docker works!
```

---

## 12. Troubleshooting: Understanding What Went Wrong

### Container Exited Immediately
```
You ran: docker run my-app
Container: Exits without error

Cause: Default CMD finished or errored
Fix: Check logs
  docker logs <container-id>
  docker logs --tail 50 <container-id>  # Last 50 lines
  docker logs -f <container-id>         # Follow live logs

Example:
  docker run -it my-app bash
  └─ Start container with bash shell to debug
```

### Can't Connect to Container
```
You ran: docker run -p 8000:5000 my-app
But: Can't reach localhost:8000

Cause 1: App not listening on port 5000
  docker exec -it <container-id> netstat -tlnp
  └─ Shows which ports container is listening on

Cause 2: Port mapping wrong
  docker port <container-id>
  └─ Shows actual port mappings

Cause 3: Firewall blocking
  docker run -p 0.0.0.0:8000:5000 my-app
  └─ Explicitly bind to all interfaces
```

### Image Huge or Build Slow
```
Image size bloated: 1 GB for Python app ❌

Cause 1: Large base image
  ❌ FROM ubuntu:20.04 (77 MB)
  ✓ FROM python:3.11-slim (125 MB total, not 200)

Cause 2: Cache invalidation
  ❌ COPY . /app (copies everything)
  ✓ COPY requirements.txt /app/ (only dependencies)

Cause 3: Not cleaning up
  ❌ RUN apt-get install X && apt-get install Y
  ✓ RUN apt-get install X Y && apt-get clean

Use: docker image inspect <image-id>
└─ Shows layers and their sizes
```

---

## Summary: Docker as a System

```
DOCKER SOLVES 3 PROBLEMS:

1. Packaging
   Problem: "Works on my machine"
   Solution: Image = entire environment frozen

2. Portability
   Problem: Need different setup for dev/test/prod
   Solution: Same image runs everywhere

3. Scalability
   Problem: Need to run multiple copies
   Solution: Easy to spin up 1000 containers from same image

KEY PRINCIPLES:

✓ Image = Blueprint (immutable)
✓ Container = Instance (mutable, temporary)
✓ Registry = Distribution (version control for images)
✓ Layers = Efficiency (caching, reuse)
✓ Networking = Communication (containers talk to each other)
✓ Volumes = Persistence (data survives container deletion)

MENTAL MODEL:

Think of Docker like SHIPPING CONTAINERS:
- Image = Container design (blueprint)
- Container = Actual shipping container (with cargo)
- Registry = Port (stores containers)
- Layer = Stack of goods inside
- Network = Conveyor belts connecting containers
```

---

## Next: How This Fits Into CI/CD

See `CI_CD_PIPELINE_MENTAL_MODEL.md` for how Docker integrates into the complete automation pipeline.
