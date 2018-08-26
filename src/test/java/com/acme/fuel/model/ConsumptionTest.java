package com.acme.fuel.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.acme.fuel.Application;

@RunWith(SpringRunner.class)
@ActiveProfiles("integration")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumptionTest {
	
	@Autowired
	SmartValidator validator;

	public BindingResult validate(@ModelAttribute Consumption consumption) {
		BindingResult result = new DataBinder(consumption).getBindingResult();
	    validator.validate(consumption, result);
		return result;
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoaded() {

	}

	@Test
	public void empty() {
		// Given
		Consumption consumption = Consumption.builder().build();
		
		// When
		BindingResult result = validate(consumption);
		
		// Then
		assertTrue(result.hasErrors());
		assertEquals("NotEmpty",result.getFieldError("fuelType").getCode());
		assertEquals("NotNull",result.getFieldError("pricePerLitter").getCode());
		assertEquals("NotNull",result.getFieldError("volumeInLitters").getCode());
		assertEquals("NotNull",result.getFieldError("date").getCode());
		assertEquals("NotNull",result.getFieldError("driverId").getCode());
	}

	@Test
	public void ok() {
		// Given
		Consumption consumption = Consumption.builder()
				.fuelType("REGULAR")
				.pricePerLitter(1l)
				.volumeInLitters(1l)
				.date(LocalDateTime.now(ZoneId.systemDefault()))
				.driverId(1l)
				.build();
		
		// When
		BindingResult result = validate(consumption);
		
		// Then
		assertFalse(result.hasErrors());
	}

	@Test
	public void negatives() {
		// Given
		Consumption consumption = Consumption.builder()
				.fuelType("REGULAR")
				.pricePerLitter(-1l)
				.volumeInLitters(-1l)
				.date(LocalDateTime.now(ZoneId.systemDefault()))
				.driverId(-1l)
				.build();
		
		// When
		BindingResult result = validate(consumption);
		
		// Then
		assertTrue(result.hasErrors());
		assertEquals("Positive",result.getFieldError("pricePerLitter").getCode());
		assertEquals("Positive",result.getFieldError("volumeInLitters").getCode());
		assertEquals("Positive",result.getFieldError("driverId").getCode());
	}

	@Test
	public void fuelType() {
		// Given
		Consumption consumption = Consumption.builder()
				.fuelType("ANYTHING")
				.pricePerLitter(1l)
				.volumeInLitters(1l)
				.date(LocalDateTime.now(ZoneId.systemDefault()))
				.driverId(1l)
				.build();
		
		// When
		BindingResult result = validate(consumption);
		
		// Then
		assertTrue(result.hasErrors());
		assertEquals("Pattern",result.getFieldError("fuelType").getCode());
	}
	
}
