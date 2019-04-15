package com.carexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carexpenses.entity.RepairExpense;
import com.carexpenses.service.CarService;
import com.carexpenses.service.RepairExpenseService;

@Controller
@RequestMapping("/")
public class RepairExpenseController {

	@Autowired
	private RepairExpenseService repairExpenseService;
	
	@Autowired
	private CarService carService;
	
	@GetMapping("/repairExpense")
	public String repairExpense(Model theModel) {
		theModel.addAttribute("activeCar", carService.getActiveCar());
		theModel.addAttribute("repairExpense", new RepairExpense());
		return "repairExpense";
	}
	
	@PostMapping("/repairExpense")
	public String addRepairExpense(@ModelAttribute RepairExpense theRepairExpense, RedirectAttributes redirect) {
		
				if(carService.getActiveCar().getBrand() == null) {
					String noCarSelected = "Please select a car first.";
					redirect.addFlashAttribute("noCarSelected", noCarSelected);
				}
				else {
					theRepairExpense.setRepairTransactionIdl(0);
					repairExpenseService.addRepairExpense(theRepairExpense);
					
				}
				
				return "redirect:/repairExpense";
		
	}
	
	@GetMapping("/repairExpense/list")
	public String RepairExpenseList(Model theModel) {
		theModel.addAttribute("activeCar", carService.getActiveCar());
		theModel.addAttribute("repairExpenseList", repairExpenseService.getAll());
		return "repairExpenseList";
	}
}
