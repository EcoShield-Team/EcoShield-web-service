FROM eclipse-temurin:21-jdk-jammy

ENV TZ=America/Lima

WORKDIR /app

COPY ecoshieldService.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/app/app.jar"]
