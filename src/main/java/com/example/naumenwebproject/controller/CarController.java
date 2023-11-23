package com.example.naumenwebproject.controller;

import com.example.naumenwebproject.dto.CarDto;
import com.example.naumenwebproject.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/cars"})
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CarDto carDto) {
        this.carService.createCar(carDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping({"/{carId}"})
    public ResponseEntity<CarDto> getCar(@PathVariable Long carId) {
        CarDto carDto = carService.getCar(carId);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtos = carService.getAllCars();
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @PutMapping({"/{carId}"})
    public ResponseEntity<Void> updateCar(@PathVariable Long carId, @RequestBody CarDto carDto) {
        this.carService.updateCar(carId, carDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping({"/{carId}"})
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        this.carService.deleteCar(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}