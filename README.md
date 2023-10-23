# Phorest Project

## Overview

Standalone microservices written in Spring Boot. Each service only keeps simple CRUD operations and could be used to split out
Read/Writes depending on the scale of the system. 


## Project Setup

Start the project by using **mvn clean install** following by docker compose. This will setup a PostGres database along with all the 
services required to connect to it. 

````shell
mvn clean install 

or

mvn clean install -DskipTests
````

````shell
docker compose up -d
````

## Importing Data

Data can be imported directly from the swagger UI of data-import-api for each respective service. All csv files can be found inside the resource folder
of the data-import-api. 


````shell
curl -X POST 'http://localhost:8081/import/csv/clients' -H 'accept: */*' -H 'Content-Type: multipart/form-data' -F 'file=@clients.csv'
````

## Services

| Syntax           | Port | API Docs                                    |
|------------------|------|---------------------------------------------|
| data-import-api  | 8081 | http://localhost:8081/swagger-ui/index.html |
| clients-api      | 8082 | http://localhost:8082/swagger-ui/index.html |
| appointments-api | 8083 | http://localhost:8083/swagger-ui/index.html |
| services-api     | 8084 | http://localhost:8084/swagger-ui/index.html |
| purchases-api    | 8085 | http://localhost:8085/swagger-ui/index.html |







