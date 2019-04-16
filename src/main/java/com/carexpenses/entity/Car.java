package com.carexpenses.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="cars")	
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private int id;
	
	@NotNull
	@Column(name="model")
	@Size(min=1, message="Please insert your car model.")
	private String model;
	
	@NotNull
	@Size(min=1, message="Please insert your car brand.")
	@Column(name="brand")
	private String brand;
	
	@NotNull
	@Range(min=1900, max=2100,message="Please use a number larger than 1900.")
	@Column(name="car_year")
		private int carYear;
	
	@NotNull
	@Size(min=1, message="Please insert your car engine type.")
	@Column(name="car_engine")
	private String carEngine;
	
	
	@Column(name="owner")
	private String carOwner;
	
	@Column(name="active")
	private boolean isActive;
	
	public Car() {
	}

//	public Car(String model, String brand, int carYear, String carEngine) {
//		this.model = model;
//		this.brand = brand;
//		this.carYear = carYear;
//		this.carEngine = carEngine;
//	}
//	
	

	public Car(String model, String brand, int carYear, String carEngine, String carOwner) {
		this.model = model;
		this.brand = brand;
		this.carYear = carYear;
		this.carEngine = carEngine;
		this.carOwner = carOwner;
	}

	public int getiD() {
		return id;
	}

	public void setiD(int iD) {
		this.id = iD;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCarYear() {
		return carYear;
	}

	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}

	public String getCarEngine() {
		return carEngine;
	}

	public void setCarEngine(String carEngine) {
		this.carEngine = carEngine;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
