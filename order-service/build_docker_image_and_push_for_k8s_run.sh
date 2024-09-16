#!/bin/bash

# Fail the script on any errors
set -e

# Variables (you can customize these)
APP_NAME="order-service"
VERSION="latest"
DOCKER_REGISTRY="vitalii1992"  # Example: docker.io, AWS ECR, etc.
DOCKER_IMAGE="$DOCKER_REGISTRY/$APP_NAME:$VERSION"

# Step 1: Build the JAR using Gradle
echo "Building JAR file with Gradle..."
gradle clean build -Pkubernetes=true

# Step 2: Build Docker image
echo "Building Docker image..."
docker build -t $DOCKER_IMAGE .

# Step 3: Push Docker image to remote registry
echo "Pushing Docker image to $DOCKER_REGISTRY..."
docker push $DOCKER_IMAGE

# Optional Step 4: Output Docker image tag for reference
echo "Docker image $DOCKER_IMAGE built and pushed successfully."