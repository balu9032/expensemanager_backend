# Stage 1: Build the application using Maven and JDK 21
FROM maven:3.9.4-eclipse-temurin-21 as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build the app and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Run the app with lightweight JDK 21 image
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
