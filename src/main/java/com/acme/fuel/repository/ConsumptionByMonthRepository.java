package com.acme.fuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.reports.MonthlyConsumption;

@Repository
public interface ConsumptionByMonthRepository extends JpaRepository<MonthlyConsumption, Long> {
	
	@Query("SELECT 'REGULAR' as fuelType, 1 as volume, '2018-01-01' as date, 1 as price, 2 as totalPrice, 1 as driverId from Consumption")
	Iterable<MonthlyConsumption> consumptionByMonth(Long driverId);
	//fuelType,volume,date,price,totalPrice,driverId
	
	@Query("SELECT 'REGULAR' as fuelType, 1 as volume, '2018-01-01' as date, 1 as price, 2 as totalPrice, 1 as driverId from Consumption")
	Iterable<MonthlyConsumption> consumptionByMonth();

}
