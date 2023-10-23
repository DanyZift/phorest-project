FROM openjdk:17

WORKDIR /app

COPY target/*.jar data-import-api.jar

EXPOSE 8081

CMD ["java", "-jar", "data-import-api.jar"]