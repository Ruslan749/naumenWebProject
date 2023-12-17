package com.example.naumenwebproject.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CarDto {
    private Long id;
    private String model;
    private String brand;
    private LocalDate manufactureYear;
    private Byte[] image;
    private BigDecimal price;
}