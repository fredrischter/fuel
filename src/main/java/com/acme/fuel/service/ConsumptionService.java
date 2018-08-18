package com.acme.fuel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.repository.ConsumptionRepository;

@Service
public class ConsumptionService {
	
	@Autowired
	ConsumptionRepository repository;

	public Iterable<Consumption> findAll() {
		return repository.findAll();
	}

}
