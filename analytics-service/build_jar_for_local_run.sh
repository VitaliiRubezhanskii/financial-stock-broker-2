#!/bin/bash

# Fail the script on any errors
set -e

# Step 1: Build the JAR using Gradle
echo "Building JAR file with Gradle..."
gradle clean build -Pkubernetes=false