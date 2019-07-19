package com.carexpenses.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="cars")	
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private int id;
	
	@NotNull
	@Column(name="model")
	@Size(min=1, message="Please enter your car model.")
	private String model;
	
	@NotNull
	@Size(min=1, message="Please enter your car brand.")
	@Column(name="brand")
	private String brand;
	
	@NotNull(message="Please enter your car year.")
	@Range(min=1900, max=2100,message="Please use a number larger than 1900.")
	@Column(name="car_year")
		private int carYear;
	
	@NotNull
	@Size(min=1, message="Please enter your car engine type.")
	@Column(name="car_engine")
	private String carEngine;
		
	@Column(name="owner")
	private String carOwner;
	
	@NotNull(message="Please enter your current mileage.")
	@Range(min=1, message="Please enter your current mileage.")
	@Column(name="mileage")
	private Integer mileage;
	
	@NotNull(message="Please select your fuel type.")
	@Column(name="fuel_type")
	private String fuelType;
	
	@NotNull(message="Please set your registration date.")
	@PastOrPresent(message="Date cannot be in the future.")
	@Column(name="registration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;
	
	@Lob
	@Column(name="photo")
	
	private byte[] photoBytes;
	
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
	

	public Car(String model, String brand, int carYear, String carEngine, 
			String carOwner, Integer mileage, String fuelType, Date registrationDate,
			byte[] photoBytes) {
		this.model = model;
		this.brand = brand;
		this.carYear = carYear;
		this.carEngine = carEngine;
		this.mileage = mileage;
		this.fuelType = fuelType;
		this.registrationDate = registrationDate;
		this.carOwner = carOwner;
		this.photoBytes = photoBytes;
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

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Date getRegistrationDate() {
		
		return registrationDate; 
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public String getFormattedRegistrationDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(registrationDate);
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}
	
	
	
}
