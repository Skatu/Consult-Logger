# Use an official OpenJDK runtime as a base image
FROM openjdk:22-jdk

WORKDIR /app

# Copy the JAR file into the container at /app
COPY ConsultLogger-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]