FROM openjdk:17

WORKDIR /app

COPY target/*.jar appointments-api.jar

EXPOSE 8083

CMD ["java", "-jar", "appointments-api.jar"]