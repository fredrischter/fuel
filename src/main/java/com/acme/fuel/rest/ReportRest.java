package com.acme.fuel.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.fuel.model.reports.ConsumptionByFuelType;
import com.acme.fuel.model.reports.MoneyByMonth;
import com.acme.fuel.model.reports.MonthlyConsumption;
import com.acme.fuel.service.ConsumptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/report")
@Api(value = "report", description = "Reports")
public class ReportRest {

	private static final String SUCCESSFULLY_RETRIEVED_REPORT = "Successfully retrieved report";
	
	@Autowired
	private ConsumptionService service;

	@ApiOperation(value = "Total spent amount of money grouped by month", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = SUCCESSFULLY_RETRIEVED_REPORT) })
	@GetMapping(path = "/money-by-month")
	public Iterable<MoneyByMonth> moneyByMonth(@RequestParam(required=false) Long driverId) {
		return service.moneyByMonth(driverId);
	}

	@ApiOperation(value = "List fuel consumption records for specified month (each row should contain: fuel type, volume, date, price, total price, driver ID) ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = SUCCESSFULLY_RETRIEVED_REPORT) })
	@GetMapping(path = "/consumption-by-month/{year}/{month}")
	public Iterable<MonthlyConsumption> consumptionByMonth(@PathVariable Integer year, @PathVariable Integer month, @RequestParam(required=false) Long driverId) {
		return service.consumptionByMonth(year, month, driverId);
	}

	@ApiOperation(value = "Statistics for each month, list fuel consumption records grouped by fuel type (each row should contain: fuel type, volume, average price, total price)", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = SUCCESSFULLY_RETRIEVED_REPORT) })
	@GetMapping(path = "/consumption-by-fuel-type")
	public Iterable<ConsumptionByFuelType> consumptionByFuelType(@RequestParam(required=false) Long driverId) {
		return service.consumptionByFuelType(driverId);
	}

}
