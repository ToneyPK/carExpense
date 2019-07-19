package com.carexpenses.entity;

public class Period {
	
	private String type;
	private String startDate;
	private String endDate;
	
	public Period(String type, String startDate, String endDate) {
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}





}
