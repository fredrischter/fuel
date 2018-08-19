package com.acme.fuel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.repository.ConsumptionRepository;
import com.acme.fuel.service.exception.ConsumptionNotFoundException;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
	
	@Autowired
	ConsumptionRepository repository;

	@Override
	public Consumption retrieve(Long id) {
		Optional<Consumption> consumption = repository.findById(id);
		if (!consumption.isPresent()) {
			throw new ConsumptionNotFoundException();
		}
		return consumption.get();
	}

	@Override
	public void create(Consumption consumption) {
		repository.save(consumption);
	}

	@Override
	public Iterable<Consumption> list() {
		return repository.findAll();
	}

}
