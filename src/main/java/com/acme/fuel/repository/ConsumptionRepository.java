package com.acme.fuel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.Consumption;

@Repository
public interface ConsumptionRepository extends CrudRepository<Consumption, Long> {

	@Query("SELECT avg(c.pricePerLitter) from Consumption c")
	Integer getAverage();

}
