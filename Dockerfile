FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/workshop-server-0.0.1-SNAPSHOT.jar workshop.jar

COPY .env .env
COPY .env.prod .env.prod 

COPY global-project-config/logback-spring.xml /app/global-project-config/logback-spring.xml

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "workshop.jar"]
