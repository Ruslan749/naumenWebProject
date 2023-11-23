package com.example.naumenwebproject.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String massage) {
        super(massage);
    }
}
