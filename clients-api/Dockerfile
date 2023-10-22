FROM openjdk:17

WORKDIR /app

COPY target/*.jar clients-api.jar

EXPOSE 8080

CMD ["java", "-jar", "clients-api.jar"]