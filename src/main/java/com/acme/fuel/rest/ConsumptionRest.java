package com.acme.fuel.rest;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.service.ConsumptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/consumption")
@Api(value = "consumption", description = "Operations on consumption register")
public class ConsumptionRest {

	@Autowired
	private ConsumptionService service;

	@GetMapping(path = "/{id}")
	public Consumption retrieve(@PathVariable Long id) {
		return service.retrieve(id);
	}

	@PostMapping()
	public void create(@RequestBody @Valid Consumption consumption) {
		service.create(consumption);
	}

	@ApiOperation(value = "View a list of consumptions", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	@GetMapping(path = "/list")
	public Iterable<Consumption> list() {
		return service.list();
	}

	@GetMapping(path = "/month")
	public int byMonth(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate month) {
		return service.byMonth(month);
	}

}
