package com.example.naumenwebproject.mapper;

import com.example.naumenwebproject.dto.OrderDto;
import com.example.naumenwebproject.model.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public final OrderItemMapper orderItemMapper;

    private final PersonMapper personMapper;

    public OrderMapper(OrderItemMapper orderItemMapper, PersonMapper personMapper) {
        this.orderItemMapper = orderItemMapper;
        this.personMapper = personMapper;
    }


    public OrderDto orderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(LocalDateTime.now());
        orderDto.setPaid(false);
        orderDto.setActive(true);
        orderDto.setPersonDto(personMapper.personToDto(order.getPerson()));
        orderDto.setOrderItems(orderItemMapper.orderItemsToDtoList(order.getOrderItems()));

        return orderDto;
    }

    public List<OrderDto> orderToDtoList(List<Order> order) {
        return order.stream()
                .map(this::orderToDto)
                .collect(Collectors.toList());
    }
}
