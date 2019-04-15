package com.carexpenses.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carexpenses.dao.RepairExpenseRepository;
import com.carexpenses.entity.Car;
import com.carexpenses.entity.RepairExpense;

@Service
public class RepairExpenseServiceImpl implements RepairExpenseService {

	@Autowired
	private RepairExpenseRepository repairExpenseRepository;

	@Autowired
	private CarService carService;
	
	@Override
	public List<RepairExpense> getAll() {
		
	//	return repairExpenseRepository.findAll();
		return getActiveRepairExpenses();
	}

	@Override
	public void addRepairExpense(RepairExpense theRepairExpense) {
		int carId = carService.getActiveCar().getiD();
		theRepairExpense.setCarId(carId);
		repairExpenseRepository.save(theRepairExpense);

	}

	@Override
	public List<RepairExpense> getActiveRepairExpenses() {
		List<RepairExpense> allRepairExpenses = repairExpenseRepository.findAll();
		List<RepairExpense> activeRepairExpenses = new ArrayList<RepairExpense>();
		Car activeCar= carService.getActiveCar();
		
		for (RepairExpense repairExpense : allRepairExpenses) {
			
			if(repairExpense.getCarId() == activeCar.getiD()) {
				activeRepairExpenses.add(repairExpense);
				
			}
			
		}
		
		return activeRepairExpenses;
		
	}

}
