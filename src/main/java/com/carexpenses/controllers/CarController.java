package com.carexpenses.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carexpenses.entity.Car;
import com.carexpenses.service.CarService;

@Controller
@RequestMapping("/")
public class CarController {

	private CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/cars")
	public String findAll(Model theModel){
		theModel.addAttribute("getCar",carService.getCarsByOwner());
		theModel.addAttribute("car", new Car());
		return "cars.html";
		
	}
	
	@PostMapping("/cars/activate/{id}")
	public String setActiveCar(@PathVariable int id) {
			carService.setActiveCar(id);
			return "redirect:/";
	}
	
	@GetMapping("/addCar")
	public String addCar(Car car, Model theModel){
		theModel.addAttribute("car", new Car());
		return "addCar";
		
	}
	
	@PostMapping("/cars/{id}")
	public String removeCarById(@PathVariable int id) {
		carService.removeCarById(id);
		return "redirect:/cars";
	}
	
	@PostMapping("/cars")
	public String addCar(@Valid @ModelAttribute Car theCar, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/addCar";
		}
		theCar.setiD(0);
		carService.addCar(theCar);
		return "redirect:cars";
	}
	
}
