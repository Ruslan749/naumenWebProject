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
        Car car = carMapper.dtoToCar(carDto);
        carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new CarNotFoundException("Car with ID " + carId + " not found");
        } else {
            carRepository.deleteById(carId);
        }
    }

    public void updateCar(Long carId, CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();
            Car updatedCar = carMapper.dtoToCar(carDto);

            existingCar.setModel(updatedCar.getModel());
            existingCar.setBrand(updatedCar.getBrand());
            existingCar.setManufactureYear(updatedCar.getManufactureYear());
            existingCar.setImage(updatedCar.getImage());
            existingCar.setPrice(updatedCar.getPrice());

            carRepository.save(existingCar);
        } else {
            throw new CarNotFoundException("Car with ID " + carId + " not found");
        }
    }

    public CarDto getCar(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            return carMapper.carToDto(optionalCar.get());
        } else {
            throw new CarNotFoundException("Car with ID " + carId + " not found");
        }
    }

    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(carMapper::carToDto).collect(Collectors.toList());
    }
}