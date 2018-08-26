package com.acme.fuel.rest;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.acme.fuel.Application;
import com.acme.fuel.repository.ConsumptionRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("integration")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportRestTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	ConsumptionRepository consumptionRepository; 
	
	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
		
		consumptionRepository.deleteAll();
		
		given()
			.port(port)
			.multiPart("file", new ClassPathResource("bulkfuel2.csv").getFile(), "text/csv")
			.when().post("/consumption/bulk")
			.then()
			.statusCode(200);
		
	}

	@Test
	public void contextLoaded() {

	}

	@Test
	public void moneyByMonth() {
		String expectedJSON = "[{\"totalSpent\":1145,\"month\":\"2018-02\"},{\"totalSpent\":1205,\"month\":\"2018-03\"},{\"totalSpent\":1265,\"month\":\"2018-04\"}]";

		given()
			.port(port)
			.contentType("application/json")
			.when().get("/report/money-by-month")
			.then()
			.statusCode(200)
			.body(IsEqual.equalTo(expectedJSON));
		
	}

	@Test
	public void moneyByMonthByDriver() {
		String expectedJSON = "[{\"totalSpent\":189,\"month\":\"2018-02\"},{\"totalSpent\":201,\"month\":\"2018-03\"},{\"totalSpent\":213,\"month\":\"2018-04\"}]";

		given()
			.port(port)
			.contentType("application/json")
			.when().get("/report/money-by-month?driverId=3")
			.then()
			.statusCode(200)
			.body(IsEqual.equalTo(expectedJSON));
		
	}

	@Test
	public void consumptionByMonth() {
		String expectedJSON = "[{\"fuelType\":\"REGULAR\",\"volumeInLitters\":11,\"date\":\"2018-02-01T10:01:12\",\"price\":1,\"totalPrice\":11,\"driverId\":1},{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":12,\"date\":\"2018-02-01T10:01:12\",\"price\":1,\"totalPrice\":12,\"driverId\":1},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":21,\"date\":\"2018-02-01T10:01:12\",\"price\":2,\"totalPrice\":42,\"driverId\":2},{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":22,\"date\":\"2018-02-01T10:01:12\",\"price\":2,\"totalPrice\":44,\"driverId\":2},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":31,\"date\":\"2018-02-01T10:01:12\",\"price\":3,\"totalPrice\":93,\"driverId\":3},{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":32,\"date\":\"2018-02-01T10:01:12\",\"price\":3,\"totalPrice\":96,\"driverId\":3},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":41,\"date\":\"2018-02-01T10:01:12\",\"price\":4,\"totalPrice\":164,\"driverId\":4},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":42,\"date\":\"2018-02-01T10:01:12\",\"price\":4,\"totalPrice\":168,\"driverId\":4},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":51,\"date\":\"2018-02-01T10:01:12\",\"price\":5,\"totalPrice\":255,\"driverId\":5},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":52,\"date\":\"2018-02-01T10:01:12\",\"price\":5,\"totalPrice\":260,\"driverId\":5}]";

		given()
			.port(port)
			.contentType("application/json")
			.when().get("/report/consumption-by-month/2018/02")
			.then()
			.statusCode(200)
			.body(IsEqual.equalTo(expectedJSON));
		
	}

	@Test
	public void consumptionByMonthByDriver() {
		String expectedJSON = "[{\"fuelType\":\"REGULAR\",\"volumeInLitters\":45,\"date\":\"2018-04-01T10:01:12\",\"price\":4,\"totalPrice\":180,\"driverId\":4},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":46,\"date\":\"2018-04-01T10:01:12\",\"price\":4,\"totalPrice\":184,\"driverId\":4}]";

		given()
			.port(port)
			.contentType("application/json")
			.when().get("/report/consumption-by-month/2018/04?driverId=4")
			.then()
			.statusCode(200)
			.body(IsEqual.equalTo(expectedJSON));
		
	}

	@Test
	public void consumptionByFuelType() {
		String expectedJSON = "[{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":78,\"month\":\"2018-04\",\"averagePrice\":2.2564102564102564,\"totalPrice\":176},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":249,\"month\":\"2018-02\",\"averagePrice\":3.9879518072289155,\"totalPrice\":993},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":263,\"month\":\"2018-03\",\"averagePrice\":3.958174904942966,\"totalPrice\":1041},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":277,\"month\":\"2018-04\",\"averagePrice\":3.931407942238267,\"totalPrice\":1089},{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":66,\"month\":\"2018-02\",\"averagePrice\":2.303030303030303,\"totalPrice\":152},{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":72,\"month\":\"2018-03\",\"averagePrice\":2.2777777777777777,\"totalPrice\":164}]";

		given()
			.port(port)
			.contentType("application/json")
			.when().get("/report/consumption-by-fuel-type")
			.then()
			.statusCode(200)
			.body(IsEqual.equalTo(expectedJSON));
		
	}

	@Test
	public void consumptionByFuelTypeByDriver() {
		String expectedJSON = "[{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":36,\"month\":\"2018-04\",\"averagePrice\":3.0,\"totalPrice\":108},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":31,\"month\":\"2018-02\",\"averagePrice\":3.0,\"totalPrice\":93},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":33,\"month\":\"2018-03\",\"averagePrice\":3.0,\"totalPrice\":99},{\"fuelType\":\"REGULAR\",\"volumeInLitters\":35,\"month\":\"2018-04\",\"averagePrice\":3.0,\"totalPrice\":105},{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":32,\"month\":\"2018-02\",\"averagePrice\":3.0,\"totalPrice\":96},{\"fuelType\":\"PREMIUM\",\"volumeInLitters\":34,\"month\":\"2018-03\",\"averagePrice\":3.0,\"totalPrice\":102}]";

		given()
			.port(port)
			.contentType("application/json")
			.when().get("/report/consumption-by-fuel-type?driverId=3")
			.then()
			.statusCode(200)
			.body(IsEqual.equalTo(expectedJSON));
		
	}
	
}
