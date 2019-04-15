package com.carexpenses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carexpenses.entity.FuelExpense;

public interface FuelExpenseRepository extends JpaRepository<FuelExpense, Integer> {

}
