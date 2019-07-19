package com.carexpenses.service;

import java.util.List;

import com.carexpenses.entity.ServiceExpense;

public interface ServiceExpenseService {

	List<ServiceExpense> getAll();
	void addServiceExpense(ServiceExpense theFuelExpense);
	List<ServiceExpense> getActiveServiceExpenses();
	List<ServiceExpense> serviceExpensesByUser();
	List<ServiceExpense> getServiceExpensesByCarId(int id);
	
}
