package com.example.naumenwebproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CarDto {
    private String model;
    private String brand;
    private LocalDate manufactureYear;
    private Byte[] image;
    private BigDecimal price;
}