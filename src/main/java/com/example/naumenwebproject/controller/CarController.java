package com.example.naumenwebproject.controller;

import com.example.naumenwebproject.dto.CarDto;
import com.example.naumenwebproject.exception.CarNotFoundException;
import com.example.naumenwebproject.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/api/cars")
    public ResponseEntity<Void> createCar(@RequestBody CarDto carDto) {
        carService.createCar(carDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("getCar/{carId}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long carId) {
        try {
            CarDto carDto = carService.getCar(carId);
            return ResponseEntity.ok(carDto);
        } catch (CarNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtos = carService.getAllCars();
        return ResponseEntity.ok(carDtos);
    }

    @PutMapping("getCar/{carId}")
    public ResponseEntity<Void> updateCar(@PathVariable Long carId, @RequestBody CarDto carDto) {
        try {
            carService.updateCar(carId, carDto);
            return ResponseEntity.ok().build();
        } catch (CarNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("getCar/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        try {
            carService.deleteCar(carId);
            return ResponseEntity.noContent().build();
        } catch (CarNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}