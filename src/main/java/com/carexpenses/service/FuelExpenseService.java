package com.carexpenses.service;

import java.util.List;

import com.carexpenses.entity.FuelExpense;

public interface FuelExpenseService{

	public List<FuelExpense> getAll();
	public void addFuelExpense(FuelExpense theFuelExpense);
	public List<FuelExpense> getActiveFuelExpenses();
	
}
