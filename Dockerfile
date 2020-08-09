FROM openjdk:8-jdk-alpine

COPY target/profile-service-0.0.1-SNAPSHOT.jar /app/profile-service.jar

CMD ["java", "-jar", "/app/profile-service.jar"]