FROM openjdk:11-jdk-slim
EXPOSE 8000

COPY target/resume-api.jar /resume-api.jar

ENTRYPOINT [ "java", "-jar", "resume-api.jar" ]