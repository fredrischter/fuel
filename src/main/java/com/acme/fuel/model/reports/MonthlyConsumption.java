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
@Entity // TODO We are having a side effect that need to be fixed: this entity is being created by JPA engine, but hasn't to be created, so some solution need to be done to address it, or not.
public class MonthlyConsumption {
	
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	String fuelType;
	
	Long volumeInLitters;

	@Id
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	LocalDateTime date;
	
	Long price;

	Long totalPrice;
	
	Long driverId;
}
