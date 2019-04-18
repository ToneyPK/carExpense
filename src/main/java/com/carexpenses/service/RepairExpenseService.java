package com.carexpenses.service;

import java.util.Date;
import java.util.List;

import com.carexpenses.entity.FuelExpense;
import com.carexpenses.entity.RepairExpense;

public interface RepairExpenseService {

	public List<RepairExpense> getAll();
	public void addRepairExpense(RepairExpense theFuelExpense);
	public List<RepairExpense> getActiveRepairExpenses();
	
	
}
