package com.example.naumenwebproject.service;

import com.example.naumenwebproject.dto.CarDto;
import com.example.naumenwebproject.exception.CarNotFoundException;
import com.example.naumenwebproject.mapper.CarMapper;
import com.example.naumenwebproject.model.Car;
import com.example.naumenwebproject.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public void createCar(CarDto carDto) {
        Car car = this.carMapper.dtoToCar(carDto);
        this.carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        if (!this.carRepository.existsById(carId)) {
            throw new CarNotFoundException("Car with ID " + carId + " not found");
        } else {
            this.carRepository.deleteById(carId);
        }
    }

    public void updateCar(Long carId, CarDto carDto) {
        Optional<Car> optionalCar = this.carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car existingCar = (Car)optionalCar.get();
            this.carMapper.dtoToCar(carDto);
            this.carRepository.save(existingCar);
        } else {
            throw new CarNotFoundException("Car with ID " + carId + " not found");
        }
    }

    public CarDto getCar(Long carId) {
        Optional<Car> optionalCar = this.carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            return this.carMapper.carToDto((Car)optionalCar.get());
        } else {
            throw new CarNotFoundException("Car with ID " + carId + " not found");
        }
    }

    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(carMapper::carToDto).collect(Collectors.toList());
    }
}