package com.carexpenses.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.carexpenses.dao.ServiceExpenseRepository;
import com.carexpenses.entity.ServiceExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.carexpenses.entity.Car;

@Service
public class ServiceExpenseServiceImpl implements ServiceExpenseService {

	@Autowired
	private ServiceExpenseRepository serviceExpenseRepository;

	@Autowired
	private CarService carService;
		    	
	@Override
	public List<ServiceExpense> getAll() {

		return getActiveServiceExpenses();
	}
	
	@Override
	public List<ServiceExpense> serviceExpensesByUser(){
		String user  = getLoggedInUsername();
	    return serviceExpenseRepository.serviceExpensesByOwner(user);

	}

	@Override
	public void addServiceExpense(ServiceExpense theServiceExpense) {
		int carId = carService.getActiveCar().getiD();
		theServiceExpense.setCarId(carId);

		String user  = getLoggedInUsername();
		theServiceExpense.setOwner(user);
		
		carService.getActiveCar().setMileage(theServiceExpense.getMileage());
		
		Date date = new Date();
		theServiceExpense.setDate(date);
		
		serviceExpenseRepository.save(theServiceExpense);

	}

	@Override
	public List<ServiceExpense> getActiveServiceExpenses() {
		List<ServiceExpense> allServiceExpenses = serviceExpenseRepository.findAll();
		List<ServiceExpense> activeServiceExpenses = new ArrayList<ServiceExpense>();
		Car activeCar= carService.getActiveCar();
		
		for (ServiceExpense serviceExpense : allServiceExpenses) {
			
			if(serviceExpense.getCarId() == activeCar.getiD()) {
				activeServiceExpenses.add(serviceExpense);
				
			}
			
		}
		
		return activeServiceExpenses;
		
	}
	
	public List<ServiceExpense> getServiceExpensesByCarId(int id){
		List<ServiceExpense> allServiceExpenses = serviceExpenseRepository.findAll();
		List<ServiceExpense> serviceExpenseFiltered = new ArrayList<ServiceExpense>();

		for (ServiceExpense serviceExpense : allServiceExpenses) {
			if(serviceExpense.getCarId()== id) {
				serviceExpenseFiltered.add(serviceExpense);
			}
		}
		return serviceExpenseFiltered;
	}

	private String getLoggedInUsername(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user  = auth.getName();

		return user;
	}

}
