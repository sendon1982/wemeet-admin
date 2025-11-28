#!/bin/bash

export IMAGE_TAG=$(date '+%Y-%m-%d')

# Build and push image to GCR
mvn clean package -DskipTests -Pprod

docker build -t australia-southeast1-docker.pkg.dev/gps-root-main/wemeet-ecr/wemeet-admin:$IMAGE_TAG-SNAPSHOT .

docker push australia-southeast1-docker.pkg.dev/gps-root-main/wemeet-ecr/wemeet-admin:$IMAGE_TAG-SNAPSHOT

gcloud run deploy wemeet-admin --image=australia-southeast1-docker.pkg.dev/gps-root-main/wemeet-ecr/wemeet-admin:$IMAGE_TAG-SNAPSHOT --region=australia-southeast1 --platform=managed --allow-unauthenticated --project=gps-root-main
