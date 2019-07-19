package com.carexpenses.controllers;

import com.carexpenses.entity.Car;
import com.carexpenses.entity.ServiceExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carexpenses.service.CarService;
import com.carexpenses.service.ServiceExpenseService;

import java.util.List;

@Controller
@RequestMapping("/")
public class ServiceExpenseController {

	@Autowired
	private ServiceExpenseService serviceExpenseService;
	
	@Autowired
	private CarService carService;
	
	@GetMapping("/serviceExpense")
	public String renderServiceExpensePage(Model theModel) {
		Car activeCar = carService.getActiveCar();
		int currentMileage = activeCar.getMileage();
		ServiceExpense serviceExpense = new ServiceExpense();

		theModel.addAttribute("currentMileage", currentMileage);
		theModel.addAttribute("activeCar", activeCar);
		theModel.addAttribute("serviceExpense", serviceExpense);
		return "serviceExpense";
	}
	
	@PostMapping("/serviceExpense")
	public String addRepairExpense(@ModelAttribute ServiceExpense theServiceExpense, RedirectAttributes redirect) {
		
				if(carService.getActiveCar().getBrand() == null) {
					String noCarSelected = "Please select a car first.";
					redirect.addFlashAttribute("noCarSelected", noCarSelected);
				}
				else {
					theServiceExpense.setServiceTransactionIdl(0);
					serviceExpenseService.addServiceExpense(theServiceExpense);
					
					String infoMessage = "You have successfully added " + theServiceExpense.getServiceName()
					+ " at the price of " + theServiceExpense.getServiceCost() + " €";
					
					redirect.addFlashAttribute("successMessage",infoMessage);
				}
				
				return "redirect:/serviceExpense";
		
	}
	
	@GetMapping("/serviceExpense/list")
	public String ServiceExpenseList(Model theModel) {
		Car activeCar = carService.getActiveCar();
		List<ServiceExpense> allServiceExpenses = serviceExpenseService.getAll();

		theModel.addAttribute("activeCar", activeCar);
		theModel.addAttribute("serviceExpenseList", allServiceExpenses);

		return "serviceExpenseList";
	}
}
