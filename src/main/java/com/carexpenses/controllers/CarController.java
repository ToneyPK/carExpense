package com.carexpenses.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.carexpenses.entity.Car;
import com.carexpenses.service.CarService;

@Controller
@RequestMapping("/")
public class CarController {

	private CarService carService;

	@Autowired
	private DashboardController dc;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/cars")
	public String renderCarsPage(Model theModel){
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
	public String renderAddCarPage(Car car, Model theModel){
		theModel.addAttribute("car", new Car());
		return "/addCar";

	}

	@GetMapping("/updateCar")
	public String renderUpdateCarPage(Model theModel){
		Car activeCar = carService.getActiveCar();
		String photoBytes = dc.getPhotoBytes();

		theModel.addAttribute("car", activeCar);
		theModel.addAttribute("photoBytes", photoBytes);
		return "updateCar";
	}

	@PostMapping("/updateCar")
	public String updateCar(@Valid @ModelAttribute Car theCar, BindingResult bindingResult,@RequestParam("photo") MultipartFile photo){

		if (bindingResult.hasErrors()){
			return "/updateCar";
		}
		updateCarPhoto(theCar,photo);
		carService.updateCar(theCar);

		return "redirect:/";
	}

	@PostMapping("/cars/{id}")
	public String removeCarById(@PathVariable int id) {
		carService.removeCarById(id);
		return "redirect:/cars";
	}

	@PostMapping("/cars")
	public String addCar(@Valid @ModelAttribute Car theCar, BindingResult bindingResult,
						 @RequestParam("photo") MultipartFile photo) {
		if(bindingResult.hasErrors()) {
			return "/addCar";
		}
		setCarPhoto(theCar,photo);
		theCar.setiD(0);
		carService.addCar(theCar);
		return "redirect:cars";
	}

	public void setCarPhoto(Car theCar, MultipartFile photo){
		if (!(photo.isEmpty())){
			try {
				byte[] convertedBase64Bytes = convertBytesToBase64(photo.getBytes());
				theCar.setPhotoBytes(convertedBase64Bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			byte[] result = {};
			theCar.setPhotoBytes(result);
		}
	}

	public void updateCarPhoto(Car theCar, MultipartFile photo){
		if (!(photo.isEmpty())){
			try {
				byte[] convertedBase64Bytes = convertBytesToBase64(photo.getBytes());
				theCar.setPhotoBytes(convertedBase64Bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			theCar.setPhotoBytes(carService.getActiveCar().getPhotoBytes());
		}
	}

	private byte[] convertBytesToBase64(byte[] bytes){
		byte[] base64Bytes = Base64.encodeBase64(bytes);
		return base64Bytes;
	}
}
