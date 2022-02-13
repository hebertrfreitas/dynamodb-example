FROM gradle:7.4.0-jdk11 as builder
WORKDIR /src
COPY . .
RUN gradle bootJar

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder src/build/libs/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app/app.jar"]
