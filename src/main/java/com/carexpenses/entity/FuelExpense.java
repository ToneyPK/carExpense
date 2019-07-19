package com.carexpenses.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="fuel_expenses")
public class FuelExpense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fuel_transaction_id")
	private int fuelTransactionId;
	
	@Column(name="amount_liters")
	private double amountOfLiters;
	
	@Column(name="amount_paid")
	private double amountPaid;
	
	@Column(name="fuel_price")
	private double fuelPrice;
	
	@Column(name="fuel_consumption")
	private double fuelConsumption;
	
	@Column(name = "car_id")
	private int carId;
	
	@Column(name="owner")
	private String owner;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)  
	private Date date;
	
	public FuelExpense() {
		
	}

	public FuelExpense(double amountOfLiters, double amountPaid, double fuelPrice, 
			int carId, Date date, double fuelConsumption, String owner) {
		this.amountOfLiters = amountOfLiters;
		this.amountPaid = amountPaid;
		this.carId = carId;
		this.date = date;
		this.fuelPrice = fuelPrice;
		this.fuelConsumption = fuelConsumption;
		this.owner = owner;
	}

	public int getFuelTransactionId() {
		return fuelTransactionId;
	}

	public void setFuelTransactionId(int fuelTransactionId) {
		this.fuelTransactionId = fuelTransactionId;
	}

	public double getAmountOfLiters() {
		return amountOfLiters;
	}

	public void setAmountOfLiters(double amountOfLiters) {
		this.amountOfLiters = amountOfLiters;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
		//return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getFuelPrice() {
		return fuelPrice;
	}

	public void setFuelPrice(double fuelPrice) {
		this.fuelPrice = fuelPrice;
	}

	public double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	
	
}
