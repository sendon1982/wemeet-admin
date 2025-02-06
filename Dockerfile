# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set a directory for the app
WORKDIR /app

# Copy the JAR file to the container
COPY target/wemeet-admin-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that the Spring Boot app listens on
EXPOSE 8080

# Optionally, you can set the default active profile if needed:
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
