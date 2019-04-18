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
	
	@Column(name = "car_id")
	private int carId;
	
	
	@Column(name = "date")
	private Date date;
	
	public FuelExpense() {
		
	}

	public FuelExpense(double amountOfLiters, double amountPaid, int carId, Date date) {
		this.amountOfLiters = amountOfLiters;
		this.amountPaid = amountPaid;
		this.carId = carId;
		this.date = date;
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
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");;
		return dateFormat.format(date);
		//return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
