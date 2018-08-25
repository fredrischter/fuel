package com.acme.fuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.fuel.model.reports.MoneyByMonth;

@Repository
public interface MoneyByMonthRepository extends JpaRepository<MoneyByMonth, Long> {

	@Query("SELECT 1 as totalSpent, '2018-01-01' as month from Consumption")
	Iterable<MoneyByMonth> moneyByMonth(Long driverId);

	@Query("SELECT 1 as totalSpent, '2018-01-01' as month from Consumption")
	Iterable<MoneyByMonth> moneyByMonth();

}
