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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String user  = auth.getName(); //get logged in username
		
		theCar.setCarOwner(user);
		carRepository.save(theCar);
	}

	@Override
	@Transactional
	public void removeCarById(int id) {
		carRepository.deleteById(id);
		
	}

	@Override
	
	public List<Car> getCarsByOwner() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String user  = auth.getName(); //get logged in username
		
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
