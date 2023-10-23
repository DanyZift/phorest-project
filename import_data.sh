#!/bin/bash

# Define your curl commands
curl_command_1="curl -X POST 'http://localhost:8081/api/v1/import/clients' -H 'accept: */*' -H 'Content-Type: multipart/form-data' -F 'file=@/data-import-api/src/main/resources/data/clients.csv'"
curl_command_2="curl -X POST 'http://localhost:8081/api/v1/import/appointments' -H 'accept: */*' -H 'Content-Type: multipart/form-data' -F 'file=@/data-import-api/src/main/resources/data/appointments.csv'"
curl_command_3="curl -X POST 'http://localhost:8081/api/v1/import/services' -H 'accept: */*' -H 'Content-Type: multipart/form-data' -F 'file=@/data-import-api/src/main/resources/data/services.csv'"
curl_command_4="curl -X POST 'http://localhost:8081/api/v1/import/purchases' -H 'accept: */*' -H 'Content-Type: multipart/form-data' -F 'file=@/data-import-api/src/main/resources/data/purchases.csv'"

# Execute the curl commands one by one
echo "Importing client data ..."
$curl_command_1
echo "Client data complete. "

echo "Importing appointments data"
$curl_command_2
echo "Appointments data complete."

echo "Importing services data"
$curl_command_3
echo "Services data done."

echo "Importing purchases data"
$curl_command_4
echo "Purchases data done."