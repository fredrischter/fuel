package com.acme.fuel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.model.reports.ConsumptionByFuelType;
import com.acme.fuel.model.reports.MoneyByMonth;
import com.acme.fuel.model.reports.MonthlyConsumption;

@Service
public interface ConsumptionService {
	
	public Consumption retrieve(Long id);

	public Consumption create(Consumption consumption);

	public Iterable<Consumption> list();

	public List<Consumption> bulkCreate(MultipartFile csvFile);

	public Iterable<MoneyByMonth> moneyByMonth(Long driverId);

	public Iterable<MonthlyConsumption> consumptionByMonth(Integer year, Integer month, Long driverId);

	public Iterable<ConsumptionByFuelType> consumptionByFuelType(Long driverId);
}
