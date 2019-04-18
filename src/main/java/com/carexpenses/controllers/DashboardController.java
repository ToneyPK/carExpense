package com.carexpenses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carexpenses.entity.Car;
import com.carexpenses.entity.FuelExpense;
import com.carexpenses.entity.RepairExpense;
import com.carexpenses.service.CarService;
import com.carexpenses.service.FuelExpenseService;
import com.carexpenses.service.RepairExpenseService;

@Controller
@RequestMapping("/")
public class DashboardController {

	@Autowired
	private FuelExpenseService fuelExpenseService;

	@Autowired
	private RepairExpenseService repairExpenseService;

	@Autowired
	private CarService carService;

	@GetMapping("/")
	public String visitDashboard(Model theModel) {


		//Total fuel expenses
		List<FuelExpense> allFuelExpenses = fuelExpenseService.getAll();

		double totalFuelExpenses = 0.0;

		for (FuelExpense fuelExpense : allFuelExpenses) {
			totalFuelExpenses += fuelExpense.getAmountPaid();
		}

		//Total Repair expenses

		List<RepairExpense> allRepairExpenses = repairExpenseService.getAll();

		double totalRepairExpenses = 0.0;

		for (RepairExpense repairExpense : allRepairExpenses) {
			totalRepairExpenses += repairExpense.getRepairCost();
		}

		//Total expenses in general

		double totalExpenses = totalFuelExpenses + totalRepairExpenses;

		//active car
		Car activeCar = carService.getActiveCar();
		String noCarSelected = "";

		if(activeCar.getBrand() == null) {
			//activeCar = new Car();
			noCarSelected = "Please select a car.";

		}

		//Model Attributes
		theModel.addAttribute("allFuelExpenses", String.format("%.2f", totalFuelExpenses) );
		theModel.addAttribute("allRepairExpenses", String.format("%.2f", totalRepairExpenses) );
		theModel.addAttribute("totalExpenses", String.format("%.2f", totalExpenses) );
		theModel.addAttribute("activeCar", activeCar);
		theModel.addAttribute("noCarSelected", noCarSelected);

		return "dashboard";

	}
}
