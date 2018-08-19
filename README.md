# Fuel
Fuel Consumption API



## Testing the application with curl commands

Register new consumption

curl -X POST http://localhost:8080/consumption/ --header "Content-Type: application/json" -d "{\"driverId\":\"1234\", \"fuelType\": \"regular\", \"volumeInLitters\": 1000, \"date\": \"2018-07-06T10:20:30\", \"pricePerLitter\": 30 }" -i

Get consumption register by Id

curl -X GET http://localhost:8080/consumption/1 -i

List consumption registers

curl -X GET http://localhost:8080/consumption/list -i
