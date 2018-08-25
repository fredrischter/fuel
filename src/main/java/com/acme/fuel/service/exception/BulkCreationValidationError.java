package com.acme.fuel.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BulkCreationValidationError extends FuelException {
	
	private static final long serialVersionUID = -7829224652180921661L;

	public BulkCreationValidationError(BindingResult result) {
		super(result.getAllErrors().toString());
	}

}
