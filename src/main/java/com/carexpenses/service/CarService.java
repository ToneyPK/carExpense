package com.carexpenses.service;

import java.util.List;

import com.carexpenses.entity.Car;

public interface CarService {

	public List<Car> findAll();
	public void addCar(Car theCar);
	public void removeCarById(int id);
	public Car findCarById(int id);
	public List<Car> getCarsByOwner();
	public void setActiveCar(int id);
	public Car getActiveCar();
	
	
	
	
	
}
