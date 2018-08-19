package com.acme.fuel.service;

import org.springframework.stereotype.Service;

import com.acme.fuel.model.Consumption;

@Service
public interface ConsumptionService {
	
	public Consumption retrieve(Long id);

	public void create(Consumption consumption);

	public Iterable<Consumption> list();

}
