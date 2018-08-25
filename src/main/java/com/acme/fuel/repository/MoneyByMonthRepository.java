package com.acme.fuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.reports.MoneyByMonth;

@Repository
public interface MoneyByMonthRepository extends JpaRepository<MoneyByMonth, Long> {

	@Query("SELECT new com.acme.fuel.model.reports.MoneyByMonth(sum(c.pricePerLitter*c.volumeInLitters), year(c.date), month(c.date)) from Consumption c where c.driverId=?1 group by year(c.date), month(c.date)")
	Iterable<MoneyByMonth> moneyByMonth(Long driverId);

	@Query("SELECT new com.acme.fuel.model.reports.MoneyByMonth(sum(c.pricePerLitter*c.volumeInLitters), year(c.date), month(c.date)) from Consumption c group by year(c.date), month(c.date)")
	Iterable<MoneyByMonth> moneyByMonth();

}
