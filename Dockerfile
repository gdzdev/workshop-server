# Etapa 1: Compilar el proyecto
FROM maven:3.9.5-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la app con JDK liviano
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/workshop-server-0.0.1-SNAPSHOT.jar workshop.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "workshop.jar"]
