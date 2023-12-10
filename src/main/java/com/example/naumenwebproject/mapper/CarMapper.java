package com.example.naumenwebproject.mapper;

import com.example.naumenwebproject.dto.CarDto;
import com.example.naumenwebproject.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarDto carToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setModel(car.getModel());
        carDto.setBrand(car.getBrand());
        carDto.setManufactureYear(car.getManufactureYear());
        carDto.setImage(car.getImage());
        carDto.setPrice(car.getPrice());
        return carDto;
    }

    public Car dtoToCar(CarDto carDto) {
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setBrand(carDto.getBrand());
        car.setManufactureYear(carDto.getManufactureYear());
        car.setImage(carDto.getImage());
        car.setPrice(carDto.getPrice());
        return car;
    }
}