package com.acme.fuel.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	// TODO Insert more API documentation annotations
	@GetMapping(path = "/{id}")
	public Consumption retrieve(@PathVariable Long id) {
		return service.retrieve(id);
	}

	// TODO Should return the generated id.
	@PostMapping()
	public void create(@RequestBody @Valid Consumption consumption) {
		service.create(consumption);
	}

	// TODO Should return the generated ids.
	@PostMapping(path = "/bulk")
	public void bulkCreate(@RequestPart(name = "file", required = false) MultipartFile csvFile) {
		service.bulkCreate(csvFile);
	}

	@ApiOperation(value = "View a list of consumptions", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	@GetMapping(path = "/list")
	public Iterable<Consumption> list() {
		return service.list();
	}

}
