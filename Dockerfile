# Stage 1: Build the app using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy everything (pom.xml + src)
COPY . .

# Build the app and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Run the app with lightweight JDK image
FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/*.jar /app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
