package com.acme.fuel.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO split in DTO / Entity instead of using Entity as DTO
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

	// TODO Make it enum and validated with options REGULAR, PREMIUM or let it for a open format, accepting  95, 98 or D for example.
	@NotEmpty
	@Pattern(regexp = "^(REGULAR|PREMIUM)$")
	String fuelType;
	
	@NotNull
	@Positive
	Long pricePerLitter;

	@NotNull
	@Positive
	Long volumeInLitters;
	
	// TODO remove this annotation and make an global datetime format configuration
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@NotNull
	LocalDateTime date;

	@NotNull
	@Positive
	Long driverId;
	
}
