package com.carexpenses.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.carexpenses.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		List<FuelExpense> activeFuelExpenses = getActiveFuelExpenses();
		
		 return activeFuelExpenses;
	}
	
	public List<FuelExpense> fuelExpensesByUser(){
	    String user  = getLoggedInUsername();
		
	    return fuelExpenseRepository.fuelExpensesByOwner(user);

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

	    String user  = getLoggedInUsername();
		theFuelExpense.setOwner(user);
		
		Date currentDate = new Date();
		theFuelExpense.setDate(currentDate);
		
		double totalExpense = theFuelExpense.getFuelPrice() * theFuelExpense.getAmountOfLiters();
		theFuelExpense.setAmountPaid(totalExpense);
		
		theFuelExpense.setFuelTransactionId(0);
		 fuelExpenseRepository.save(theFuelExpense);
	}
	
	private String getLoggedInUsername(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username  = auth.getName();

		return username;
	}

}
