package com.carexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carexpenses.entity.FuelExpense;
import com.carexpenses.service.CarService;
import com.carexpenses.service.FuelExpenseService;

@Controller
@RequestMapping("/")
public class FuelExpenseController {

	@Autowired
	private FuelExpenseService fuelExpenseService;
	
	@Autowired
	CarService carService;
	
	@GetMapping("/fuelExpense")
	public String fuelExpense(Model theModel) {
		theModel.addAttribute("activeCar", carService.getActiveCar());
		theModel.addAttribute("fuelExpense", new FuelExpense());
		return "fuelExpense";
		
	}
	
	@PostMapping("/fuelExpense")
	public String addExpense(@ModelAttribute FuelExpense theFuelExpense, RedirectAttributes redirect) {
		
		//String noCarSelected = "";
		if(carService.getActiveCar().getBrand() == null) {
			String noCarSelected = "Please select a car first.";
			redirect.addFlashAttribute("noCarSelected", noCarSelected);
		}
		else {
			theFuelExpense.setFuelTransactionId(0);
			fuelExpenseService.addFuelExpense(theFuelExpense);
			
		}
		
		return "redirect:/fuelExpense";
		
	}
	
	@GetMapping("/fuelExpense/list")
	public String fuelExpenseList(Model theModel) {
		theModel.addAttribute("activeCar", carService.getActiveCar());
		theModel.addAttribute("fuelExpenseList", fuelExpenseService.getAll());
		return "fuelExpenseList";
		
	}
}
