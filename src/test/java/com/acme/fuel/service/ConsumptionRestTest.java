package com.acme.fuel.service;

import static com.jayway.restassured.RestAssured.given;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.acme.fuel.Application;

@RunWith(SpringRunner.class)
@ActiveProfiles("integration")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumptionRestTest {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoaded() {

	}

	@Test
	public void createDoingOk() {
		String myJson = "{\"driverId\":\"1234\", \"fuelType\": \"REGULAR\", \"volumeInLitters\": 1000, \"date\": \"2018-07-06T10:20:30\", \"pricePerLitter\": 30 }";

		given()
			.port(port)
			.contentType("application/json")
			.body(myJson)
			.when().post("/consumption")
			.then()
			.statusCode(200);
		
	}

	@Test
	public void createGettingError() {
		String myJson = "zzzzzzzzzzzz";

		given()
			.port(port)
			.contentType("application/json")
			.body(myJson)
			.when().post("/consumption")
			.then()
			.statusCode(400);
		
	}

	@Test
	public void createGettingInvalidFuelType() {
		String myJson = "{\"driverId\":\"1234\", \"fuelType\": \"xaaaaa\", \"volumeInLitters\": 1000, \"date\": \"2018-07-06T10:20:30\", \"pricePerLitter\": 30 }";

		given()
			.port(port)
			.contentType("application/json")
			.body(myJson)
			.when().post("/consumption")
			.then()
			.statusCode(400)
			.body(IsEqual.equalTo("[\"fuelType: must match \\\"^(REGULAR|PREMIUM)$\\\"\"]"));
		
	}
	
	//TODO more tests

}
