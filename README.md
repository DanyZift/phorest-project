# Phorest Project

## Overview

Standalone microservices written in Spring Boot and built using mvn parent/super pom. Each service only keeps simple CRUD operations and could be used to split out
Read/Writes depending on the scale of the system. All services are bundled together here for ease of use but would normally be put into
separate repos. 


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
Once docker has been run all services will be started along with a single PostGres database for testing
locally. The service list can be found below:

## Services

| Syntax          | Port | API Docs                                    |
|-----------------|------|---------------------------------------------|
| data-api  | 8081 | http://localhost:8081/swagger-ui/index.html |
| clients-api     | 8082 | http://localhost:8082/swagger-ui/index.html |
| appointments-api | 8083 | http://localhost:8083/swagger-ui/index.html |
| services-api    | 8084 | http://localhost:8084/swagger-ui/index.html |
| purchases-api   | 8085 | http://localhost:8085/swagger-ui/index.html |


## Importing Data

Data can be imported directly from the swagger UI of data-api for each respective service. All csv files can be found inside the resource folder
of the data-api.


````shell
curl -X POST 'http://localhost:8081/import/csv/clients' -H 'accept: */*' -H 'Content-Type: multipart/form-data' -F 'file=@clients.csv'
````

## Further Work

I was planning on creating a GraphQl endpoint along with the mutations to pull the data directly out of the DB and keeping the services
all light weight. The data-api was going to be used for this. Other choices I was considering was setting up an Orchestration-based Saga pattern
leveraging off Kafka to control the flow of data. 

One item that is missing is the clients with the most loyalty points. I needed to update the data-api to set the one to many, many to many relationships
and either implement it via graphql or JPA using custom queries or a db view. 

````sql
select c.id,
       c.first_name,
       c.last_name,
       c.email,
       count(a.client_id) as total_appointments,
       sum(p.price) as total_price,
       sum(p.loyalty_points) as total_loyalty
from clients c
         join appointments a on c.id = a.client_id
         join purchases p on p.appointment_id = a.id
where banned = false
group by c.id, c.first_name, c.last_name, c.email
order by total_appointments desc
limit 50;

````






