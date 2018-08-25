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
	
	Long volumeInLitters;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	LocalDateTime month;
	
	Double averagePrice;

	Long totalPrice;

	public ConsumptionByFuelType(String fuelType, long volumeInLitters, int year, int month, double averagePrice, long totalPrice) {
		this.fuelType = fuelType;
		this.volumeInLitters = volumeInLitters;
		this.month = LocalDateTime.of(year, month, 1, 0, 0);
		this.averagePrice = averagePrice;
		this.totalPrice = totalPrice;
	}
}
