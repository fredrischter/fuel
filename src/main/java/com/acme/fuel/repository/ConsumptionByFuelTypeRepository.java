package com.acme.fuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.reports.ConsumptionByFuelType;

@Repository
public interface ConsumptionByFuelTypeRepository extends JpaRepository<ConsumptionByFuelType, Long> {
	
	@Query("SELECT new com.acme.fuel.model.reports.ConsumptionByFuelType(c.fuelType, sum(c.volumeInLitters), year(c.date), month(c.date), avg(c.pricePerLitter), sum(c.pricePerLitter*c.volumeInLitters)) from Consumption c where c.driverId=?1 group by c.fuelType, year(c.date), month(c.date)")
	Iterable<ConsumptionByFuelType> consumptionByFuelType(Long driverId);

	@Query("SELECT new com.acme.fuel.model.reports.ConsumptionByFuelType(c.fuelType, sum(c.volumeInLitters), year(c.date), month(c.date), avg(c.pricePerLitter), sum(c.pricePerLitter*c.volumeInLitters)) from Consumption c group by c.fuelType, year(c.date), month(c.date)")
	Iterable<ConsumptionByFuelType> consumptionByFuelType();

}
