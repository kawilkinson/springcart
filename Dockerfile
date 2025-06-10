# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:24-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR from your local machine to the container
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
