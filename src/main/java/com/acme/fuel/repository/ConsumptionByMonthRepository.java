package com.acme.fuel.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.reports.MonthlyConsumption;

@Repository
public interface ConsumptionByMonthRepository extends JpaRepository<MonthlyConsumption, Long> {
	
	@Query("SELECT new com.acme.fuel.model.reports.MonthlyConsumption(c.fuelType as fuelType, c.volumeInLitters as volumeInLitters, c.date as date, c.pricePerLitter as pricePerLitter, c.pricePerLitter*c.volumeInLitters as totalPrice, c.driverId as driverId) from Consumption c where c.date between ?1 and ?2 and c.driverId=?3")
	Iterable<MonthlyConsumption> consumptionByMonth(LocalDateTime start, LocalDateTime end, Long driverId);
	
	@Query("SELECT new com.acme.fuel.model.reports.MonthlyConsumption(c.fuelType as fuelType, c.volumeInLitters as volumeInLitters, c.date as date, c.pricePerLitter as pricePerLitter, c.pricePerLitter*c.volumeInLitters as totalPrice, c.driverId as driverId) from Consumption c where c.date between ?1 and ?2")
	Iterable<MonthlyConsumption> consumptionByMonth(LocalDateTime start, LocalDateTime end);

}
