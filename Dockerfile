FROM openjdk:21-jdk-slim AS build

WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar workshop.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "workshop.jar"]
