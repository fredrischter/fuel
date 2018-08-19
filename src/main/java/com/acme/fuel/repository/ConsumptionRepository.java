package com.acme.fuel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.acme.fuel.model.Consumption;

public interface ConsumptionRepository extends CrudRepository<Consumption, Long> {
	
	@Query("SELECT avg(c.pricePerLitter) from Consumption c")
	Integer getAverage();

}
