package com.example.naumenwebproject.exception;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(String massage) {
        super(massage);
    }
}
