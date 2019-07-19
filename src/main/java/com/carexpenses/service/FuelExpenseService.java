package com.carexpenses.service;

import java.util.Date;
import java.util.List;

import com.carexpenses.entity.FuelExpense;

public interface FuelExpenseService{

	List<FuelExpense> getAll();
	void addFuelExpense(FuelExpense theFuelExpense);
	List<FuelExpense> getActiveFuelExpenses();
	List<FuelExpense> fuelExpensesByUser();
}
