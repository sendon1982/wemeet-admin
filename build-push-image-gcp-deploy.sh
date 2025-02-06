#!/bin/bash

# Build and push image to GCR
./mvnw clean package -DskipTests -Pprod

./mvn dockerfile:build dockerfile:push

# Deploy to GCP cloud run
gcloud run deploy wemeet-admin --image=australia-southeast1-docker.pkg.dev/gps-root-main/wemeet-ecr/wemeet-admin:0.0.3-SNAPSHOT --region=australia-southeast1 --platform=managed --allow-unauthenticated


