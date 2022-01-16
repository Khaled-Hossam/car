package com.example.car.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.Validator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.car.dto.CarDTO;
import com.example.car.dto.CarRentDTO;
import com.example.car.exception.ResourceNotFoundException;
import com.example.car.models.Car;
import com.example.car.services.CarService;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
    Validator validator;
	
	@GetMapping
	public List<CarDTO> index(){
		var cars = carService.getAllCars();
		return cars.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@GetMapping("avilable-cars")
	public List<CarDTO> avilableCars(){
		var cars = carService.getAvilableCars();
		return cars.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public ResponseEntity<CarDTO> getCar(@PathVariable("id") int id) {
		Optional<Car> car = carService.getCarlById(id);

		if (car.isPresent()) {
			return new ResponseEntity<>(convertToDto(car.get()), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Not found car with id = " + id);
		}
	}
	
	@PostMapping
	public ResponseEntity<CarDTO> create(@Valid @RequestBody CarDTO carDto) {
		Car car = convertToEntity(carDto);
		Car carCreated = carService.create(car);
		return new ResponseEntity<>(convertToDto(carCreated), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public synchronized ResponseEntity<HttpStatus> update(@Valid @RequestBody CarDTO carDto, @PathVariable("id") int id) {
		Car car = convertToEntity(carDto);
        var carUpdated = carService.update(car, id);

		if (carUpdated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new ResourceNotFoundException("Not found car with id = " + id);
		}
	}
	
	@DeleteMapping("{id}")
	public synchronized ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
		var carDeleted =  carService.delete(id);
		if(carDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new ResourceNotFoundException("Not found car with id = " + id);
		}
	}


	@PutMapping("rent-car")
	public synchronized ResponseEntity<HashMap<String,String>> rentCar(@Valid @RequestBody CarRentDTO carRentDTO){
	    Car car = modelMapper.map(carRentDTO, Car.class);
        var carRented = carService.rentCar(car);

		if (carRented) {
			var response = new HashMap<String,String>();
			response.put("message", "car has been rented successfuly");
			return new ResponseEntity<>( response ,HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("car not found or already rented");
		}
	}


	
	private CarDTO convertToDto(Car car) {
		CarDTO carDto = modelMapper.map(car, CarDTO.class);
	    return carDto;
	}
	
	private Car convertToEntity(CarDTO carDto) {
	    Car car = modelMapper.map(carDto, Car.class);
	    return car;
	}
}
