# Fuel
Fuel Consumption API

## Testing the application with curl commands

Register new consumption

curl -X POST "http://localhost:8080/consumption" -H "Content-Type: application/json" -d "{ \"date\": \"2018-09-01T10:01:12\", \"driverId\": 1, \"fuelType\": \"REGULAR\", \"pricePerLitter\": 1, \"volumeInLitters\": 1}" -i

Get consumption register by Id

curl -X GET http://localhost:8080/consumption/1 -i

List consumption registers

curl -X GET http://localhost:8080/consumption/list -i

## Questions and answers

* Why you didn't tested the the repository?

Answer: Because it's not good practice to test the framework.

* Why you didn't tested the validation as unit test?

Answer: Because it's not good practice to test the framework - used spring default validators by annotations. So I prefer to test the validations in the integration tests.

* Why not an special name for the application?

Answer: Just "Fuel" fits well. Made a good banner in order to redress the lack of innovation in the name.


