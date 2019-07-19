package com.carexpenses.controllers;

import com.carexpenses.entity.Car;
import com.carexpenses.entity.Period;
import com.carexpenses.dao.CarRepository;
import com.carexpenses.dao.FuelExpenseRepository;
import com.carexpenses.dao.ServiceExpenseRepository;
import com.carexpenses.entity.FuelExpense;
import com.carexpenses.entity.ServiceExpense;
import com.carexpenses.service.CarService;
import com.carexpenses.service.FuelExpenseService;
import com.carexpenses.service.ServiceExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class InfoController {

	@Autowired
	CarService carService;
	
	@Autowired
	FuelExpenseService fuelExpenseService;
	
	@Autowired
	ServiceExpenseService serviceExpenseService;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ServiceExpenseRepository serviceExpenseRepository;
	
	@Autowired
	FuelExpenseRepository fuelExpenseRepository;

	DecimalFormat df = new DecimalFormat("#.##");




	@GetMapping("/info")
	public String renderInfoPage(Model model) {
		Car activeCar = carService.getActiveCar();
		List<ServiceExpense> activeServiceExpenses = serviceExpenseService.getActiveServiceExpenses();
		List<FuelExpense> activeFuelExpenses = fuelExpenseService.getActiveFuelExpenses();
		List<FuelExpense> allFuelExpensesByUser = fuelExpenseService.fuelExpensesByUser();
		double averageRepairPriceGeneral = getAverageRepairPriceGeneral();
		double averageGeneralFuelConsumption = getAverageGeneralFuelConsumption(allFuelExpensesByUser);
		double lastFuelConsumption = getLastFuelConsumptionInList(activeFuelExpenses);
		double averageGeneralFuelPrice = getAverageGeneralFuelPriceByUser();
		double averageFuelConsumption = getAverageFuelConsumptionInList(activeFuelExpenses);
		String base64Encoded = getBase64PhotoBytes();
		String lastServiceDate = getLastServiceDate(activeServiceExpenses);

		model.addAttribute("photoBytes", base64Encoded);
		model.addAttribute("lastFuelConsumption", lastFuelConsumption);
		model.addAttribute("averageGeneralFuelConsumption", averageGeneralFuelConsumption);
		model.addAttribute("averageRepairPriceGeneral",averageRepairPriceGeneral);
		model.addAttribute("averageFuelPriceGeneral", averageGeneralFuelPrice);
		model.addAttribute("lastServiceDate", lastServiceDate);
		model.addAttribute("averageFuelConsumption", averageFuelConsumption);
		model.addAttribute("averageRepairCost", getAverageRepairExpenseCost());
		model.addAttribute("activeCar", activeCar);
		
		return "info";
	}

	public double getAverageGeneralFuelPriceByUser(){
		double averageFuelPriceGeneral = 0;
		double tempAverageFuelPriceGeneral = 0;
		List<FuelExpense> allFuelExpensesByUser = fuelExpenseService.fuelExpensesByUser();
		for (FuelExpense fuelExpense : allFuelExpensesByUser) {

			tempAverageFuelPriceGeneral += fuelExpense.getFuelPrice();
		}
		averageFuelPriceGeneral = tempAverageFuelPriceGeneral / allFuelExpensesByUser.size();
		averageFuelPriceGeneral = Double.valueOf(df.format(averageFuelPriceGeneral));

		return averageFuelPriceGeneral;
	}

	private String getBase64PhotoBytes() {
		byte[] photoBytes = carService.getActiveCar().getPhotoBytes();
		String base64Encoded = null;
		base64Encoded = new String(photoBytes, StandardCharsets.UTF_8);
		return base64Encoded;
	}

	private double getAverageRepairPriceGeneral() {
		double averageRepairPriceGeneral = 0;
		double tempAverageRepairPriceGeneral = 0;
		List<ServiceExpense> allServiceExpensesByUser = serviceExpenseService.serviceExpensesByUser();

		for (ServiceExpense serviceExpense : allServiceExpensesByUser) {
			tempAverageRepairPriceGeneral += serviceExpense.getServiceCost();
		}
		averageRepairPriceGeneral = tempAverageRepairPriceGeneral / allServiceExpensesByUser.size();
		averageRepairPriceGeneral = Double.valueOf(df.format(averageRepairPriceGeneral));

		return averageRepairPriceGeneral;
	}

	private double getAverageGeneralFuelConsumption(List<FuelExpense> allFuelExpensesByUser) {
		double averageGeneralFuelConsumption = 0;
		double tempAverageGeneralFuelConsumption = 0;
		for (FuelExpense fuelExpense : allFuelExpensesByUser) {
			tempAverageGeneralFuelConsumption += fuelExpense.getFuelConsumption();
		}
		averageGeneralFuelConsumption = tempAverageGeneralFuelConsumption / allFuelExpensesByUser.size();
		averageGeneralFuelConsumption = Double.valueOf(df.format(averageGeneralFuelConsumption));

		return averageGeneralFuelConsumption;
	}

	public double getAverageFuelConsumptionInList(List<FuelExpense> activeFuelExpenses){
		double averageFuelConsumption = 0;
		double tempFuelConsumption = 0;

		double numberOfActiveFuelExpenses = activeFuelExpenses.size();

		if(!(activeFuelExpenses.isEmpty())) {
			for (FuelExpense fuelExpense : activeFuelExpenses) {
				tempFuelConsumption += fuelExpense.getFuelConsumption();
			}
			averageFuelConsumption = tempFuelConsumption / numberOfActiveFuelExpenses ;
		}else {
			averageFuelConsumption = 0;
		}

		averageFuelConsumption = Double.valueOf(df.format(averageFuelConsumption));

		return averageFuelConsumption;
	}

	public double getLastFuelConsumptionInList(List<FuelExpense> activeFuelExpenses){
		double lastFuelConsumption;
		if (!(activeFuelExpenses.isEmpty())) {
			lastFuelConsumption = activeFuelExpenses.get(activeFuelExpenses.size()-1).getFuelConsumption();
		}else {
			lastFuelConsumption = 0;
		}

		lastFuelConsumption = Double.valueOf(df.format(lastFuelConsumption));

		return lastFuelConsumption;
	}

	public String getLastServiceDate(List<ServiceExpense> activeServiceExpenses){
		String lastServiceDate;
		if(activeServiceExpenses.size()>1) {
			lastServiceDate = activeServiceExpenses.get(activeServiceExpenses.size()-1).getDate();
		}else if(activeServiceExpenses.isEmpty()) {
			lastServiceDate=" No service record found.";
		}
		else {
			lastServiceDate = activeServiceExpenses.get(0).getDate();
		}

		return lastServiceDate;
	}

	private double getAverageRepairExpenseCost(){

		List<ServiceExpense> activeServiceExpens = serviceExpenseRepository.serviceExpenseByCarId(carService.getActiveCar().getiD());
		double averageRepairExpenseCost;
		double tempCost = 0;

		for (ServiceExpense serviceExpense : activeServiceExpens) {
			tempCost +=  serviceExpense.getServiceCost();
		}
		if(!(activeServiceExpens.isEmpty())) {
			averageRepairExpenseCost = tempCost / activeServiceExpens.size();
		}else {
			averageRepairExpenseCost = 0;
		}
		averageRepairExpenseCost = Double.valueOf(df.format(averageRepairExpenseCost));

		return averageRepairExpenseCost;
	}
	
	@PostMapping("/info")
	public String setPeriodForStatistic(@ModelAttribute Period period, RedirectAttributes redirectAttributes) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		String type = period.getType();
		String startDate = period.getStartDate();
		String endDate = period.getEndDate();

		Date startDateFormatted = getParsedDate(format, startDate);
		Date endDateFormatted = getParsedDate(format, endDate);

		List<FuelExpense> fuelExpenseBetweenDateOfActiveCar = getFuelExpensesBetweenDateForActiveCar(startDateFormatted, endDateFormatted);
		List<ServiceExpense> serviceExpensesBetweenDateOfActiveCar = getServiceExpensesBetweenDateOfActiveCar(startDateFormatted, endDateFormatted);

		double averageFuelCost = 0;
		int totalFuelCost = 0;
		int numberOfFuelExpenses = fuelExpenseBetweenDateOfActiveCar.size();
		double numberOfLiters = 0;

		double averageServiceCost = 0;
		int totalServiceCost = 0;
		int numberOfServiceExpenses = serviceExpensesBetweenDateOfActiveCar.size();

		if(type.equalsIgnoreCase("Fuel")) {
			averageFuelCost = getAverageFuelCost(fuelExpenseBetweenDateOfActiveCar);
			averageFuelCost = Double.valueOf(df.format(averageFuelCost));
			totalFuelCost = getTotalFuelCost(fuelExpenseBetweenDateOfActiveCar, totalFuelCost);
			totalFuelCost = Integer.valueOf(df.format(totalFuelCost));
			numberOfLiters = getNumberOfLiters(fuelExpenseBetweenDateOfActiveCar, numberOfLiters);
		}
		else {
			averageServiceCost = getAverageServiceCost(serviceExpensesBetweenDateOfActiveCar);
			totalServiceCost = getTotalServiceCost(serviceExpensesBetweenDateOfActiveCar, totalServiceCost);
		}

		redirectAttributes.addFlashAttribute("type", type);
		redirectAttributes.addFlashAttribute("averageFuelCost", averageFuelCost);
		redirectAttributes.addFlashAttribute("totalFuelCost", totalFuelCost);
		redirectAttributes.addFlashAttribute("numberOfFuelExpenses", numberOfFuelExpenses);
		redirectAttributes.addFlashAttribute("numberOfLiters", numberOfLiters);
		redirectAttributes.addFlashAttribute("averageServiceCost", averageServiceCost);
		redirectAttributes.addFlashAttribute("totalServiceCost", totalServiceCost);
		redirectAttributes.addFlashAttribute("numberOfServiceExpenses", numberOfServiceExpenses);

		return "redirect:info";
	}

	private int getTotalServiceCost(List<ServiceExpense> serviceExpensesBetweenDateOfActiveCar, int totalServiceCost) {
		for (ServiceExpense serviceExpense : serviceExpensesBetweenDateOfActiveCar) {
			totalServiceCost += serviceExpense.getServiceCost();
		}
		return totalServiceCost;
	}

	private double getAverageServiceCost(List<ServiceExpense> serviceExpensesBetweenDateOfActiveCar) {
		double averageServiceCost;
		double allServiceCost = 0;
		for (ServiceExpense serviceExpense : serviceExpensesBetweenDateOfActiveCar) {
			allServiceCost += serviceExpense.getServiceCost();
		}
		averageServiceCost = allServiceCost / serviceExpensesBetweenDateOfActiveCar.size();
		return averageServiceCost;
	}

	private double getNumberOfLiters(List<FuelExpense> fuelExpenseBetweenDateOfActiveCar, double numberOfLiters) {
		for (FuelExpense fuelExpense : fuelExpenseBetweenDateOfActiveCar) {
			numberOfLiters += fuelExpense.getAmountOfLiters();
		}
		return numberOfLiters;
	}

	private int getTotalFuelCost(List<FuelExpense> fuelExpenseBetweenDateOfActiveCar, int totalFuelCost) {
		for (FuelExpense fuelExpense : fuelExpenseBetweenDateOfActiveCar) {
			totalFuelCost += fuelExpense.getAmountPaid();
		}
		return totalFuelCost;
	}

	private double getAverageFuelCost(List<FuelExpense> fuelExpenseBetweenDateOfActiveCar) {
		double averageFuelCost;
		double allFuelCost = 0;
		for (FuelExpense fuelExpense : fuelExpenseBetweenDateOfActiveCar) {
			allFuelCost += fuelExpense.getFuelPrice();
		}
		averageFuelCost = allFuelCost / fuelExpenseBetweenDateOfActiveCar.size();
		return averageFuelCost;
	}

	private List<ServiceExpense> getServiceExpensesBetweenDateOfActiveCar(Date startD, Date endD) {
		List<ServiceExpense> serviceExpenseBetweenDates = serviceExpenseRepository.serviceExpensesBetweenDate(startD, endD);
		List<ServiceExpense> serviceExpensesBetweenDateOfActiveCar = new ArrayList<>();

		for (ServiceExpense serviceExpense : serviceExpenseBetweenDates) {
			if (serviceExpense.getCarId() == carService.getActiveCar().getiD()){
				serviceExpensesBetweenDateOfActiveCar.add(serviceExpense);
			}
		}
		return serviceExpensesBetweenDateOfActiveCar;
	}

	private List<FuelExpense> getFuelExpensesBetweenDateForActiveCar(Date startD, Date endD) {
		List<FuelExpense> fuelExpenseBetweenDate = fuelExpenseRepository.fuelExpensesBetweenDate(startD, endD);
		List<FuelExpense> fuelExpenseBetweenDateOfActiveCar = new ArrayList<>();

		for (FuelExpense fuelExpense : fuelExpenseBetweenDate) {
			if (fuelExpense.getCarId()==carService.getActiveCar().getiD()){
				fuelExpenseBetweenDateOfActiveCar.add(fuelExpense);
			}
		}
		return fuelExpenseBetweenDateOfActiveCar;
	}

	private Date getParsedDate(SimpleDateFormat format, String startDate) {
		Date startD = null;
		try {
			startD = format.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startD;
	}

}
