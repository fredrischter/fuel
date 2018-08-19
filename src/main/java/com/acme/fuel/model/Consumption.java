package com.acme.fuel.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    
	String fuelType;
	
	Long pricePerLitter;
	
	Long volumeInLitters;
	
	LocalDateTime date;

	Long driverId;
	
}
