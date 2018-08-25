package com.acme.fuel.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.model.reports.ConsumptionByFuelType;
import com.acme.fuel.model.reports.MoneyByMonth;
import com.acme.fuel.model.reports.MonthlyConsumption;
import com.acme.fuel.repository.ConsumptionByFuelTypeRepository;
import com.acme.fuel.repository.ConsumptionByMonthRepository;
import com.acme.fuel.repository.ConsumptionRepository;
import com.acme.fuel.repository.MoneyByMonthRepository;
import com.acme.fuel.service.exception.BulkCreationValidationError;
import com.acme.fuel.service.exception.ConsumptionNotFoundException;
import com.acme.fuel.service.exception.FuelException;
import com.acme.fuel.service.factory.ConsumptionListFactory;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {

	@Autowired
	ConsumptionRepository repository;
	
	@Autowired
	ConsumptionByFuelTypeRepository consumptionByFuelTypeRepository;
	
	@Autowired
	ConsumptionByMonthRepository consumptionByMonthRepository;
	
	@Autowired
	MoneyByMonthRepository moneyByMonthRepository;
	
	@Autowired
	ConsumptionListFactory consumptionListFactory;
	
	@Autowired
	SmartValidator validator;

	public void validate(@ModelAttribute Consumption consumption, BindingResult result) {
	    validator.validate(consumption, result);
	}

	@Override
	public Consumption retrieve(Long id) {
		Optional<Consumption> consumption = repository.findById(id);
		if (!consumption.isPresent()) {
			throw new ConsumptionNotFoundException();
		}
		return consumption.get();
	}

	@Override
	public Consumption create(Consumption consumption) {
		return repository.save(consumption);
	}

	@Override
	public Iterable<Consumption> list() {
		return repository.findAll();
	}
	
	@Override
	public List<Consumption> bulkCreate(MultipartFile csvFile) {
		List<Consumption> consumptionList;
		try {
			consumptionList = consumptionListFactory.create(csvFile);
		} catch (IOException e) {
			throw new FuelException("Cannot read csv content.");
		}
		
		BindingResult result = new DataBinder(csvFile).getBindingResult();
		for (Consumption consumption : consumptionList) {
			validate(consumption, result);
		}
	    if (result.hasErrors()) {
	    	// TODO To make an exception handler in order to handle it and make proper user friendly message
	    	throw new BulkCreationValidationError(result);
	    }
		
		List<Consumption> createdList = new ArrayList<>();
		for (Consumption consumption : consumptionList) {
			createdList.add(repository.save(consumption));
		}
		return createdList;
	}

	@Override
	public Iterable<MoneyByMonth> moneyByMonth(Long driverId) {
		if (driverId != null) {
			return moneyByMonthRepository.moneyByMonth(driverId);
		} else {
			return moneyByMonthRepository.moneyByMonth();
		}
	}

	@Override
	public Iterable<MonthlyConsumption> consumptionByMonth(Integer year, Integer month, Long driverId) {
		if (driverId != null) {
			return consumptionByMonthRepository.consumptionByMonth(startOfMonth(year, month), endOfMonth(year, month), driverId);
		} else {
			return consumptionByMonthRepository.consumptionByMonth(startOfMonth(year, month), endOfMonth(year, month));
		}
	}

	private LocalDateTime startOfMonth(Integer year, Integer month) {
		return LocalDateTime.of(year, month, 1, 0, 0);
	}

	private LocalDateTime endOfMonth(Integer year, Integer month) {
		return LocalDateTime.of(year, month, 1, 0, 0).plusMonths(1l);
	}

	@Override
	public Iterable<ConsumptionByFuelType> consumptionByFuelType(Long driverId) {
		if (driverId != null) {
			return consumptionByFuelTypeRepository.consumptionByFuelType(driverId);
		} else {
			return consumptionByFuelTypeRepository.consumptionByFuelType();
		}
	}

}
