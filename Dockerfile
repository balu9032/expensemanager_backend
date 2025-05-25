# Stage 1: Build the app using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy Maven config and source code
COPY pom.xml .
COPY src ./src

# Build the app and skip tests for faster build
RUN mvn clean package -DskipTests

# Stage 2: Run the app with lightweight JDK image
FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/*.jar app.jar

# Expose port your app runs on (adjust if needed)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
