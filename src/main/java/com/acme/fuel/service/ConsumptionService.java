package com.acme.fuel.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.acme.fuel.model.Consumption;

@Service
public interface ConsumptionService {
	
	public Consumption retrieve(Long id);

	public Consumption create(Consumption consumption);

	public Iterable<Consumption> list();

	public Integer byMonth(LocalDate month);

}
