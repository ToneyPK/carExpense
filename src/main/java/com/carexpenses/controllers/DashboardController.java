package com.carexpenses.controllers;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;

import com.carexpenses.entity.ServiceExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carexpenses.entity.Car;
import com.carexpenses.entity.FuelExpense;
import com.carexpenses.service.CarService;
import com.carexpenses.service.FuelExpenseService;
import com.carexpenses.service.ServiceExpenseService;

@Controller
@RequestMapping("/")
public class DashboardController {

	@Autowired
	private FuelExpenseService fuelExpenseService;
	
	@Autowired
	private ServiceExpenseService serviceExpenseService;
	
	@Autowired
	private CarService carService;

	DecimalFormat df = new DecimalFormat("#.##");

	@GetMapping("/")
	public String renderDashboardPage(Model theModel) {
		Car activeCar = carService.getActiveCar();

		theModel.addAttribute("photoBytes", getPhotoBytes());
		theModel.addAttribute("allFuelExpenses", calculateTotalFuelExpenses());
		theModel.addAttribute("allServiceExpenses", calculateTotalServiceExpenses());
		theModel.addAttribute("totalExpenses", String.format("%.2f", calculateTotalExpenses()) );
		theModel.addAttribute("activeCar", activeCar);

		return redirectIfNoActiveCarExists(activeCar);
		
	}

	public String redirectIfNoActiveCarExists(Car car){
		if(car.getBrand() == null){
			return "redirect:/addCar";
		}else {
			return "dashboard";
		}
	}

	public String getPhotoBytes(){
		String base64Encoded = null;
		if (carService.getActiveCar().isActive()==false){
			base64Encoded = null;
		}else {
			byte[] photoBytes = carService.getActiveCar().getPhotoBytes();
			base64Encoded = new String(photoBytes, StandardCharsets.UTF_8);
		}
		return base64Encoded;
	}

	private double calculateTotalFuelExpenses(){
		List<FuelExpense> allFuelExpenses = fuelExpenseService.getAll();
		double totalFuelExpenses = 0.0;

		for (FuelExpense fuelExpense : allFuelExpenses) {
			totalFuelExpenses += fuelExpense.getAmountPaid();
		}
		totalFuelExpenses = Double.valueOf(df.format(totalFuelExpenses));

		return totalFuelExpenses;
	}

	private double calculateTotalServiceExpenses(){
		List<ServiceExpense> allServiceExpenses = serviceExpenseService.getAll();
		double totalServiceExpenses = 0.0;

		for (ServiceExpense serviceExpense : allServiceExpenses) {
			totalServiceExpenses += serviceExpense.getServiceCost();
		}
		totalServiceExpenses = Double.valueOf(df.format(totalServiceExpenses));

		return totalServiceExpenses;
	}

	private double calculateTotalExpenses(){
		double  totalExpenses = calculateTotalFuelExpenses() + calculateTotalServiceExpenses();
		totalExpenses = Double.valueOf(df.format(totalExpenses));

		return totalExpenses;
	}

}
