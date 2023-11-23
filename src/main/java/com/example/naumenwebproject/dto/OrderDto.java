package com.example.naumenwebproject.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private List<OrderItemDto> orderItems;
    private Boolean paid;
    private LocalDateTime date;

}
