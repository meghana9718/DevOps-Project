# Student Management System

A simple student management application with Java backend and HTML/CSS/JS frontend.

## Project Structure
```
DevOps-Project/
├── Main.java              # Java main application
├── Student.java           # Student model
├── StudentService.java    # Student service layer
├── Dockerfile            # Docker configuration
├── index.html            # Frontend UI
├── style.css             # Styling
└── script.js             # Frontend logic
```

## Running Locally

### Compile and Run Server
```bash
cd DevOps-Project
javac Main.java Student.java StudentService.java
java Main
```
Then open browser at: http://localhost:8080

## Docker Containerization

### Build Docker Image
```bash
docker build -t student-app .
```

### Run Container
```bash
docker run -p 9090:9090 student-app
```
Then open browser at: http://localhost:9090

### Run Container in Background
```bash
docker run -d -p 9090:9090 --name student-container student-app
```

## Docker Commands

### List Images
```bash
docker images
```

### List Running Containers
```bash
docker ps
```

### Stop Container
```bash
docker stop student-container
```

### Remove Container
```bash
docker rm student-container
```

### Remove Image
```bash
docker rmi student-app
```

## Deployment

### Push to Docker Hub
```bash
docker tag student-app <your-dockerhub-username>/student-app:latest
docker login
docker push <your-dockerhub-username>/student-app:latest
```

### Pull and Run from Docker Hub
```bash
docker pull <your-dockerhub-username>/student-app:latest
docker run -d -p 9090:9090 <your-dockerhub-username>/student-app:latest
```

### Deploy to Kubernetes
```bash
kubectl create deployment student-app --image=<your-dockerhub-username>/student-app:latest
kubectl expose deployment student-app --type=LoadBalancer --port=9090
kubectl get services
```

## Requirements
- Java 17+
- Docker (for containerization)
- Modern web browser (for frontend)

## Jenkins Pipeline

### Prerequisites
1. Jenkins with Docker installed
2. Docker Hub credentials added to Jenkins (ID: `dockerhub-credentials`)
3. Update `DOCKER_REGISTRY` in Jenkinsfile with your Docker Hub username

### Setup
1. Create new Pipeline job in Jenkins
2. Point to your Git repository
3. Set Script Path to `Jenkinsfile`
4. Run the pipeline

### Pipeline Stages
- **Checkout**: Clone repository
- **Build**: Build Docker image
- **Test**: Verify Java installation
- **Push**: Push to Docker Hub
- **Deploy**: Run container on port 9090
