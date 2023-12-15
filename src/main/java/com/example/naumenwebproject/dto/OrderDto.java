package com.example.naumenwebproject.dto;

import com.example.naumenwebproject.model.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private List<OrderItemDto> orderItems;
    private Boolean paid;
    private LocalDateTime date;
    private Boolean active;
    private Person person;

}
