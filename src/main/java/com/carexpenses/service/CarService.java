package com.carexpenses.service;

import java.util.List;

import com.carexpenses.entity.Car;

public interface CarService {

	List<Car> findAll();
	void addCar(Car theCar);
	void removeCarById(int id);
	Car findCarById(int id);
	List<Car> getCarsByOwner();
	void setActiveCar(int id);
	Car getActiveCar();
	void updateCar(Car theCar);

}
