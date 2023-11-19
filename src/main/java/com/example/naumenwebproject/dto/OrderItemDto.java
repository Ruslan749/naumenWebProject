package com.example.naumenwebproject.dto;

import com.example.naumenwebproject.model.Car;
import com.example.naumenwebproject.model.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemDto {
    private Long id;
    private Car car;
    private Integer quantity;
    private Order order;
    private LocalDateTime expireTime;
    private Boolean expired;
}
