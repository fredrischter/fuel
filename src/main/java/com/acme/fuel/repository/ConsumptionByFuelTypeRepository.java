package com.acme.fuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.reports.ConsumptionByFuelType;

@Repository
public interface ConsumptionByFuelTypeRepository extends JpaRepository<ConsumptionByFuelType, Long> {
	
	@Query("SELECT 'REGULAR' as fuelType, 1 as volume, '2018-01-01' as month, 1 as averagePrice, 2 as totalPrice from Consumption")
	Iterable<ConsumptionByFuelType> consumptionByFuelType(Long driverId);
	//fuelType,volume,month,averagePrice,totalPrice

	@Query("SELECT 'REGULAR' as fuelType, 1 as volume, '2018-01-01' as month, 1 as averagePrice, 2 as totalPrice from Consumption")
	Iterable<ConsumptionByFuelType> consumptionByFuelType();

}
