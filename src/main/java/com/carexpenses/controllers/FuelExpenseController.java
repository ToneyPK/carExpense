package com.carexpenses.controllers;

import java.util.List;

import com.carexpenses.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carexpenses.dao.FuelExpenseRepository;
import com.carexpenses.entity.FuelExpense;
import com.carexpenses.service.CarService;
import com.carexpenses.service.FuelExpenseService;

@Controller
@RequestMapping("/")
public class FuelExpenseController {

	@Autowired
	private FuelExpenseService fuelExpenseService;
	
	@Autowired
	private FuelExpenseRepository fuelExpenseRepository;
	
	@Autowired
	private CarService carService;

	@GetMapping("/fuelExpense")
	public String renderFuelExpensePage(Model theModel) {
		Car activeCar = carService.getActiveCar();
		FuelExpense fuelExpense = new FuelExpense();

		theModel.addAttribute("lastFuelPrice", getLastFuelPrice());
		theModel.addAttribute("activeCar", activeCar);
		theModel.addAttribute("fuelExpense", fuelExpense);

		return "fuelExpense";
	}
	
	@PostMapping("/fuelExpense")
	public String addExpense(@ModelAttribute FuelExpense theFuelExpense, RedirectAttributes redirect) {
				
		if(carService.getActiveCar().getBrand() == null) {
			String noCarSelected = "Please select a car first.";
			redirect.addFlashAttribute("noCarSelected", noCarSelected);
		}
		else {
			theFuelExpense.setFuelTransactionId(0);
			fuelExpenseService.addFuelExpense(theFuelExpense);

			String infoMessage = "You have successfully added " + theFuelExpense.getAmountOfLiters() 
			+ "l at the price of " + theFuelExpense.getFuelPrice() + " €";
			String totalAmountMessage = "Total amount paid: " + theFuelExpense.getAmountPaid() + " €";

			redirect.addFlashAttribute("totalAmountMessage",totalAmountMessage);
			redirect.addFlashAttribute("successMessage",infoMessage);
		}
		
		return "redirect:/renderFuelExpensePage";
		
	}
	
	@GetMapping("/fuelExpense/list")
	public String fuelExpenseList(Model theModel) {
		theModel.addAttribute("activeCar", carService.getActiveCar());
		theModel.addAttribute("fuelExpenseList", fuelExpenseService.getAll());

		return "fuelExpenseList";
		
	}

	public double getLastFuelPrice(){
		Integer activeCarId = carService.getActiveCar().getiD();
		List<FuelExpense>activeFuelExpenses = fuelExpenseRepository.fuelExpensesByCarId(activeCarId);
		double lastFuelPrice;

		if(!(activeFuelExpenses.isEmpty())) {
			lastFuelPrice = activeFuelExpenses.get(activeFuelExpenses.size()-1).getFuelPrice();
		}else {
			lastFuelPrice = 0.0;
		}

		return lastFuelPrice;
	}
}
