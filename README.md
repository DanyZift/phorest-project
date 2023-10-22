# Phorest Project


## Project Setup

Kick off the services via docker compose 

````shell
mvn clean install 

docker compose up -d
````


## Importing Data

**Clients**
````shell
curl -X POST 'http://localhost:8081/import/csv/clients' -H 'accept: */*' -H 'Content-Type: multipart/form-data' -F 'file=@clients.csv'
````