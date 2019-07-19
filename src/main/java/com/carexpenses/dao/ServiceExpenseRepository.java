package com.carexpenses.dao;

import java.util.Date;
import java.util.List;

import com.carexpenses.entity.ServiceExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceExpenseRepository extends JpaRepository<ServiceExpense, Integer> {

	@Query("SELECT c FROM ServiceExpense c WHERE c.carId = :id " )
    List<ServiceExpense> serviceExpenseByCarId(@Param("id") int id);
	
	@Query("SELECT c FROM ServiceExpense c WHERE c.owner = :owner " )
    List<ServiceExpense> serviceExpensesByOwner(@Param("owner") String owner);

	@Query ("SELECT e FROM ServiceExpense e WHERE e.date BETWEEN :startDate AND :endDate")
    List<ServiceExpense> serviceExpensesBetweenDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
