package com.acme.fuel.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Consumption {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    
	String fuelType;
	
	Long pricePerLitter;
	
	Long volumeInLitters;
	
	// TODO remove this annotation and make an global datetime format configuration
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	LocalDateTime date;

	Long driverId;
	
}
