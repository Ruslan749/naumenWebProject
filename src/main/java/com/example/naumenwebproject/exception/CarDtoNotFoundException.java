package com.example.naumenwebproject.exception;

public class CarDtoNotFoundException extends RuntimeException {
    public CarDtoNotFoundException(String massage) {
        super(massage);
    }
}
