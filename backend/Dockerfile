# Stage 1: Build the Gradle Kotlin project
FROM gradle:jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy only the build files and necessary scripts
COPY build.gradle.kts settings.gradle.kts /app/
COPY src /app/src/

# Build the project
RUN gradle build

# Stage 2: Create the final Docker image
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the application's port (if needed)
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
