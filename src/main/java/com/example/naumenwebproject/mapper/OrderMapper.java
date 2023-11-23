package com.example.naumenwebproject.mapper;

import com.example.naumenwebproject.dto.OrderDto;
import com.example.naumenwebproject.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto orderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setPaid(order.getPaid());

        return orderDto;
    }
}
