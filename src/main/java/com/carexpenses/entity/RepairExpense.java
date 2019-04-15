package com.carexpenses.entity;

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
	
	public RepairExpense() {
		
	}

	public RepairExpense(String repairName, double repairCost) {
		this.repairName = repairName;
		this.repairCost = repairCost;
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
	
	
}

