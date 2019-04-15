package com.carexpenses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carexpenses.entity.RepairExpense;

public interface RepairExpenseRepository extends JpaRepository<RepairExpense, Integer> {

}
