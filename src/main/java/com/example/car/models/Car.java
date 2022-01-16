package com.example.car.models;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;
	
	private String model;
	
	private String customerName;
	
	private LocalDate rentedFrom;
	
	private LocalDate rentedFor;

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getRentedFrom() {
		return rentedFrom;
	}

	public void setRentedFrom(LocalDate rentedFrom) {
		this.rentedFrom = rentedFrom;
	}

	public LocalDate getRentedFor() {
		return rentedFor;
	}

	public void setRentedFor(LocalDate rentedFor) {
		this.rentedFor = rentedFor;
	}
	
	
}
