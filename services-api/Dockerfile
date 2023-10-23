FROM openjdk:17

WORKDIR /app

COPY target/*.jar services-api.jar

EXPOSE 8084

CMD ["java", "-jar", "services-api.jar"]