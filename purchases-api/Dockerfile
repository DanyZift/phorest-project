FROM openjdk:17

WORKDIR /app

COPY target/*.jar purchases-api.jar

EXPOSE 8085

CMD ["java", "-jar", "purchases-api.jar"]