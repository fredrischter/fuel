# Fuel
Fuel Consumption API

## Testing the application with curl commands

Register new consumption

curl -X POST "http://localhost:8080/consumption" -H "Content-Type: application/json" -d "{ \"date\": \"2018-09-01T10:01:12\", \"driverId\": 1, \"fuelType\": \"REGULAR\", \"pricePerLitter\": 1, \"volumeInLitters\": 1}" -i

Get consumption register by Id

curl -X GET http://localhost:8080/consumption/1 -i

List consumption registers

curl -X GET http://localhost:8080/consumption/list -i

Bulk create consumption

curl -X POST -F 'file=@src/test/resources/bulkfuel1.csv' "http://localhost:8080/consumption/bulk" -i

The CSV format must to be like:

```text
<fuel type>;<price per litter>;<volume in litters>;<date>;<driver>
<fuel type>;<price per litter>;<volume in litters>;<date>;<driver>
<fuel type>;<price per litter>;<volume in litters>;<date>;<driver>
```

Example:

REGULAR;1;1;2018-09-01T10:01:12;1
REGULAR;2;4;2018-09-01T10:01:12;4
REGULAR;1;2;2018-09-01T10:01:12;2

## Reports

### load sample csv number 2

curl -X POST -F 'file=@src/test/resources/bulkfuel2.csv' "http://localhost:8080/consumption/bulk" -i

### money by month

curl -X GET http://localhost:8080/report/money-by-month -i

curl -X GET http://localhost:8080/report/money-by-month?driverId=3 -i

[
	{
		totalSpent: 189,
		month: "2018-02"
	},
	{
		totalSpent: 201,
		month: "2018-03"
	},
	{
		totalSpent: 213,
		month: "2018-04"
	}
]

### consumption records by month

curl -X GET http://localhost:8080/report/consumption-by-month/2018/02 -i

curl -X GET http://localhost:8080/report/consumption-by-month/2018/04?driverId=4 -i

[
	{
		fuelType: "REGULAR",
		volumeInLitters: 45,
		date: "2018-04-01T10:01:12",
		price: 4,
		totalPrice: 180,
		driverId: 4
	},
	{
		fuelType: "REGULAR",
		volumeInLitters: 46,
		date: "2018-04-01T10:01:12",
		price: 4,
		totalPrice: 184,
		driverId: 4
	}
]

### consumption records by fuel type

curl -X GET http://localhost:8080/report/consumption-by-fuel-type -i

[
	{
		fuelType: "PREMIUM",
		volumeInLitters: 78,
		month: "2018-04",
		averagePrice: 2.2564102564102564,
		totalPrice: 176
	},
	{
		fuelType: "REGULAR",
		volumeInLitters: 249,
		month: "2018-02",
		averagePrice: 3.9879518072289155,
		totalPrice: 993
	},
	{
		fuelType: "REGULAR",
		volumeInLitters: 263,
		month: "2018-03",
		averagePrice: 3.958174904942966,
		totalPrice: 1041
	},
	{
		fuelType: "REGULAR",
		volumeInLitters: 277,
		month: "2018-04",
		averagePrice: 3.931407942238267,
		totalPrice: 1089
	},
	{
		fuelType: "PREMIUM",
		volumeInLitters: 66,
		month: "2018-02",
		averagePrice: 2.303030303030303,
		totalPrice: 152
	},
	{
		fuelType: "PREMIUM",
		volumeInLitters: 72,
		month: "2018-03",
		averagePrice: 2.2777777777777777,
		totalPrice: 164
	}
]

curl -X GET http://localhost:8080/report/consumption-by-fuel-type?driverId=3 -i

[
	{
		fuelType: "PREMIUM",
		volumeInLitters: 36,
		month: "2018-04",
		averagePrice: 3,
		totalPrice: 108
	},
	{
		fuelType: "REGULAR",
		volumeInLitters: 31,
		month: "2018-02",
		averagePrice: 3,
		totalPrice: 93
	},
	{
		fuelType: "REGULAR",
		volumeInLitters: 33,
		month: "2018-03",
		averagePrice: 3,
		totalPrice: 99
	},
	{
		fuelType: "REGULAR",
		volumeInLitters: 35,
		month: "2018-04",
		averagePrice: 3,
		totalPrice: 105
	},
	{
		fuelType: "PREMIUM",
		volumeInLitters: 32,
		month: "2018-02",
		averagePrice: 3,
		totalPrice: 96
	},
	{
		fuelType: "PREMIUM",
		volumeInLitters: 34,
		month: "2018-03",
		averagePrice: 3,
		totalPrice: 102
	}
]

## Questions and answers

* Why you didn't tested the the repository?

Answer: Because it's not good practice to test the framework.

* Why you didn't tested the validation as unit test?

Answer: Because it's not good practice to test the framework - used spring default validators by annotations. So I prefer to test the validations in the integration tests.

* Why not an special name for the application?

Answer: Just "Fuel" fits well. Made a good banner in order to redress the lack of innovation in the name.
