package com.example.car.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.car.models.Car;
import com.example.car.repositories.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> getAvilableCars(){
		return carRepository.findByRentedForLessThanOrRentedForIsNull(LocalDate.now());
	}
	
	public Car create(Car car) {
		return carRepository.save(car);
	}

	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	public Optional<Car> getCarlById(int id) {
		return carRepository.findById(id);
	}

    public boolean update(Car updatedCar, int id) {
		if (!carRepository.existsById(id)) {
			return false;
		}

		updatedCar.setCarId(id);
		carRepository.save(updatedCar);
		return true;
    }

	public boolean delete(int id) {
		if (!carRepository.existsById(id)) {
			return false;
		}
		
		carRepository.deleteById(id);
		return true;
	}

	public boolean rentCar(Car rentCar) {
		var car = carRepository.findById(rentCar.getCarId());

		if (!car.isPresent() || carIsRented(car.get())) {
			return false;
		}
		rentCar.setModel(car.get().getModel());
		carRepository.save(rentCar);
		return true;
	}

	private boolean carIsRented(Car car) {
		if(car.getRentedFor() != null && car.getRentedFor().isAfter(LocalDate.now()) ){
			return true;
		}
		return false;
	}

	
}
