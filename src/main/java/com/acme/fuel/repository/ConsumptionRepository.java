package com.acme.fuel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.Consumption;

@Repository
public interface ConsumptionRepository extends CrudRepository<Consumption, Long> {
	
	Iterable<Consumption> findByDriverId(Long id);
	
	Iterable<Consumption> findByDriverIdIn(List<Long> id);

}
