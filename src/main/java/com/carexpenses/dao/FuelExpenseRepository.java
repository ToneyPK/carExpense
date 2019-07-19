package com.carexpenses.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carexpenses.entity.FuelExpense;


public interface FuelExpenseRepository extends JpaRepository<FuelExpense, Integer> {

	@Query("SELECT c FROM FuelExpense c WHERE c.carId = :id " )
    List<FuelExpense> fuelExpensesByCarId(@Param("id") int id);
	
	@Query("SELECT c FROM FuelExpense c WHERE c.owner = :owner" )
    List<FuelExpense> fuelExpensesByOwner(@Param("owner") String owner);
	
	@Query ("SELECT e FROM FuelExpense e WHERE e.date BETWEEN :startDate AND :endDate")
    List<FuelExpense> fuelExpensesBetweenDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
