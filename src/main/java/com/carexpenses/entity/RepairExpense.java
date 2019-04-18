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
@Table(name="repair_expenses")
public class RepairExpense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="repair_transaction_id")
	private int repairTransactionIdl;
	
	@Column(name="repair_name")
	private String repairName;
	
	@Column(name="repair_cost")
	private double repairCost;
	
	@Column(name = "car_id")
	private int carId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "description")
	private String description;
	
	public RepairExpense() {
		
	}
	
	public RepairExpense(String repairName, double repairCost, int carId, Date date, String description) {
		this.repairName = repairName;
		this.repairCost = repairCost;
		this.carId = carId;
		this.date = date;
		this.description = description;
	}

	public int getRepairTransactionIdl() {
		return repairTransactionIdl;
	}

	public void setRepairTransactionIdl(int repairTransactionIdl) {
		this.repairTransactionIdl = repairTransactionIdl;
	}

	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}

	public double getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(double repairCost) {
		this.repairCost = repairCost;
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
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}

