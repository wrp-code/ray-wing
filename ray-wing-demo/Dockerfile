FROM eclipse-temurin:21.0.9_10-jre-alpine-3.23

LABEL maintainer=wrp

COPY target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]