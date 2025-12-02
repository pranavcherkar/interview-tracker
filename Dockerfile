# Stage 1 - Build the Spring Boot application
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy only the backend project structure
COPY Interview_tracker/interviewtracker/pom.xml .
COPY Interview_tracker/interviewtracker/src ./src

RUN mvn clean package -DskipTests

# Stage 2 - Run the built jar
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
