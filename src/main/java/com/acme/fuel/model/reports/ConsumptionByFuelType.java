package com.acme.fuel.model.reports;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ConsumptionByFuelType {
	
    private static final String DATE_FORMAT = "yyyy-MM";

	@Id
	String fuelType;
	
	Long volume;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	LocalDateTime month;
	
	Long averagePrice;

	Long totalPrice;
	
}
