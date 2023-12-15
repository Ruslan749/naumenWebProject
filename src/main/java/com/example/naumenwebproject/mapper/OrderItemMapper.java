package com.example.naumenwebproject.mapper;

import com.example.naumenwebproject.dto.OrderItemDto;
import com.example.naumenwebproject.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setExpireTime(orderItem.getExpireTime());
        orderItemDto.setQuantity(orderItem.getQuantity());

        return orderItemDto;
    }

    public OrderItem dtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setExpireTime(orderItemDto.getExpireTime());
        orderItem.setQuantity(orderItemDto.getQuantity());

        return orderItem;
    }

    public List<OrderItemDto> orderItemsToDtoList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::orderItemToDto)
                .collect(Collectors.toList());
    }
}
