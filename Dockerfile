FROM openjdk:17-alpine

COPY target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "application.jar" ]
