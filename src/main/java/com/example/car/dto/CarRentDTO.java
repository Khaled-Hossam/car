package com.example.car.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CarRentDTO {
	@NotNull
	private int carId;
	
	@NotEmpty(message = "customer name is required")
    @Size(max = 255)
	private String customerName;
	
	@NotNull
	@JsonFormat(pattern = "yyyy/MM/dd")
	private LocalDate rentedFrom;
	
	@NotNull
	@JsonFormat(pattern = "yyyy/MM/dd")
	private LocalDate rentedFor;

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
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
