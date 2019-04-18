package com.carexpenses.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carexpenses.dao.FuelExpenseRepository;
import com.carexpenses.entity.Car;
import com.carexpenses.entity.FuelExpense;

@Service
public class FuelExpenseServiceImpl implements FuelExpenseService {

	private FuelExpenseRepository fuelExpenseRepository;

	@Autowired
	private CarService carService;

	@Autowired
	public FuelExpenseServiceImpl(FuelExpenseRepository fuelExpenseRepository) {
		this.fuelExpenseRepository = fuelExpenseRepository;
	}


	@Override
	public List<FuelExpense> getAll() {
		//List<FuelExpense> allFuelExpenses = fuelExpenseRepository.findAll();
		List<FuelExpense> activeFuelExpenses = getActiveFuelExpenses();

		return activeFuelExpenses;
	}

	@Override
	public List<FuelExpense> getActiveFuelExpenses(){
		List<FuelExpense> allFuelExpenses = fuelExpenseRepository.findAll();
		List<FuelExpense> activeFuelExpenses = new ArrayList<FuelExpense>();
		Car activeCar= carService.getActiveCar();

		for (FuelExpense fuelExpense : allFuelExpenses) {

			if(fuelExpense.getCarId() == activeCar.getiD()) {
				activeFuelExpenses.add(fuelExpense);

			}

		}

		return activeFuelExpenses;

	}

	@Override
	@Transactional
	public void addFuelExpense(FuelExpense theFuelExpense) {
		int carId = carService.getActiveCar().getiD();
		theFuelExpense.setCarId(carId);
		theFuelExpense.setFuelTransactionId(0);
		fuelExpenseRepository.save(theFuelExpense);


	}



}
