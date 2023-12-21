package com.example.naumenwebproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDto {
    private Long id;
    private String model;
    private String brand;
    private Integer manufactureYear;
    private Byte[] image;
    private BigDecimal price;
}