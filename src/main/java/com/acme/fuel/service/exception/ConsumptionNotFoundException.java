package com.acme.fuel.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Consumption not found")
public class ConsumptionNotFoundException extends FuelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7736145572812087052L;

}
