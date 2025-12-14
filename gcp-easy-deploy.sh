#!/bin/bash

# --- Configuration Variables ---
export IMAGE_TAG=$(date '+%Y-%m-%d')

# GCP Artifact Registry Details
export GCP_REGION="australia-southeast1"
export GCP_PROJECT_ID="project-6e0094bd-106a-4e8e-8da"
# The host is derived from the region for Artifact Registry
export GCP_HOST="${GCP_REGION}-docker.pkg.dev"

# Artifact Registry Repository and Image Name
export AR_REPO="wemeet-ecr"
export IMAGE_NAME="wemeet-boardgame-image"

# Full Image Path
export FULL_IMAGE_PATH="${GCP_HOST}/${GCP_PROJECT_ID}/${AR_REPO}/${IMAGE_NAME}:${IMAGE_TAG}-SNAPSHOT"

# Cloud Run Service Name
export SERVICE_NAME="wemeet-boardgame-service"

# --- Build and Push Pipeline ---

echo "Building application JAR..."
# 1. Build and package the Java application
mvn clean package -DskipTests -Pprod

echo "Building Docker image: ${FULL_IMAGE_PATH}"
# 2. Build image and tag it with the full Artifact Registry path
docker build -t $FULL_IMAGE_PATH .

echo "Pushing image to Artifact Registry..."
# 3. Push image to Google Cloud Artifact Registry
docker push $FULL_IMAGE_PATH

# --- Deploy to Cloud Run ---
echo "--- Cloud Run Deployment Command ---"

# 1. Build the command as a quoted string variable
DEPLOY_COMMAND="gcloud run deploy \"$SERVICE_NAME\" \
    --image=\"$FULL_IMAGE_PATH\" \
    --region=\"$GCP_REGION\" \
    --platform=managed \
    --allow-unauthenticated \
    --project=\"$GCP_PROJECT_ID\""

# 2. Print the command for verification
echo "$DEPLOY_COMMAND"

echo "Deploying service ${SERVICE_NAME} to Cloud Run..."

eval "$DEPLOY_COMMAND"
