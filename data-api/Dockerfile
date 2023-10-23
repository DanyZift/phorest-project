FROM openjdk:17

WORKDIR /app

COPY target/*.jar data-api.jar

EXPOSE 8081

CMD ["java", "-jar", "data-api.jar"]