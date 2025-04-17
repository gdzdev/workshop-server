FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/workshop-server-0.0.1-SNAPSHOT.jar workshop.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "workshop.jar"]
