package com.acme.fuel.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.repository.ConsumptionRepository;

public class ConsumptionServiceTest {

	@Mock
	ConsumptionRepository consumtionRepository;
	
	@InjectMocks
	ConsumptionServiceImpl consumptionService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoaded() {

	}

	@Test
	public void list() {
		// Given
		when(consumtionRepository.findAll()).thenReturn(Arrays.asList(Consumption.builder().build(), Consumption.builder().build(), Consumption.builder().build()));
		
		// When
		Iterable<Consumption> response = consumptionService.list();
		List<Consumption> list = new ArrayList<Consumption>();
		response.forEach(list::add);
		
		// Then
		assertEquals(3, list.size());
	}
	
	// TODO more unit tests
}
