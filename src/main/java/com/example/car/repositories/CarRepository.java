package com.example.car.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.car.models.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	List<Car> findByRentedForLessThanOrRentedForIsNull(LocalDate date);
}
