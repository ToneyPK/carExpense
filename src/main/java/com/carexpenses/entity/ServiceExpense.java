package com.carexpenses.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="repair_expenses")
public class ServiceExpense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="repair_transaction_id")
	private int serviceTransactionIdl;
	
	@Column(name="repair_name")
	private String serviceName;
	
	@Column(name="repair_cost")
	private double serviceCost;
	
	@Column(name = "car_id")
	private int carId;
	
	@Column(name = "owner")
	private String owner;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "mileage")
	private int mileage;
	
	public ServiceExpense() {
		
	}
	
	public ServiceExpense(String serviceName, double serviceCost, int carId,
						  Date date, String description, int mileage, String owner) {
		this.serviceName = serviceName;
		this.serviceCost = serviceCost;
		this.carId = carId;
		this.date = date;
		this.description = description;
		this.mileage = mileage;
		this.owner = owner;
	}

	public int getServiceTransactionId() {
		return serviceTransactionIdl;
	}

	public void setServiceTransactionIdl(int serviceTransactionId) {
		this.serviceTransactionIdl = serviceTransactionId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
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

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	
	
}

