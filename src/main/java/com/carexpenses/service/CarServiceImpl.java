package com.carexpenses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carexpenses.dao.CarRepository;
import com.carexpenses.entity.Car;

@Service
public class CarServiceImpl implements CarService {

	private CarRepository carRepository;
	
	@Autowired
	private ServiceExpenseService serviceExpenseService;
	
	@Autowired
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public List<Car> findAll() {
		return carRepository.findAll();
	}

	@Override
	@Transactional
	public void addCar(Car theCar) {
	    String user  = getLoggedInUsername();
		
		theCar.setCarOwner(user);
		carRepository.save(theCar);
	}

	@Transactional
	@Override
	public void updateCar(Car theCar){
		Car carToSave = getActiveCar();

		carToSave.setMileage(theCar.getMileage());
		carToSave.setBrand(theCar.getBrand());
		carToSave.setCarEngine(theCar.getCarEngine());
		saveCarIfPhotoBytesExist(theCar, carToSave);
		carToSave.setFuelType(theCar.getFuelType());
		carToSave.setRegistrationDate(theCar.getRegistrationDate());
		carToSave.setCarYear(theCar.getCarYear());
		carToSave.setModel(theCar.getModel());

		carRepository.save(carToSave);
	}

	private void saveCarIfPhotoBytesExist(Car theCar, Car carToSave) {
		if(!(theCar.getPhotoBytes() == null)){
			carToSave.setPhotoBytes(theCar.getPhotoBytes());
		}
	}

	public String getLoggedInUsername(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username  = auth.getName(); //get logged in username

		return username;
	}

	@Override
	@Transactional
	public void removeCarById(int id) {
		carRepository.deleteById(id);
		
	}

	@Override
	public List<Car> getCarsByOwner() {
		String user  = getLoggedInUsername();
		
		return carRepository.listCarsByOwner(user);
	}


	@Override
	public Car findCarById(int id) {
		Optional<Car> opCar = carRepository.findById(id);
		Car car = opCar.get();
		
		return car;
		
	}

	@Override
	@Transactional
	public void setActiveCar(int id) {
		List<Car> carList = getCarsByOwner();
		for (Car car : carList) {
			car.setActive(false);
		}
		for (Car car : carList) {
			if (car.getiD() == id) {
				car.setActive(true);
			}
		}
	}

	@Override
	public Car getActiveCar() {
		Car activeCar = new Car();
		List<Car> carList = getCarsByOwner();

		for (Car car : carList) {
			if(car.isActive()==true) {
				activeCar = car;
			}
		}
		return activeCar;
	}

}
