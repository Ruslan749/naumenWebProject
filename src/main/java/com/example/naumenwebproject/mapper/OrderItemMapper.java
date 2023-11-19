package com.example.naumenwebproject.mapper;

import com.example.naumenwebproject.dto.OrderItemDto;
import com.example.naumenwebproject.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setCar(orderItem.getCar());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setOrder(orderItem.getOrder());
        orderItemDto.setExpireTime(orderItem.getExpireTime());
        orderItemDto.setExpired(orderItem.getExpired());
        return orderItemDto;
    }

    public OrderItem dtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setCar(orderItemDto.getCar());
        orderItem.setCar(orderItemDto.getCar());
        orderItem.setOrder(orderItemDto.getOrder());
        orderItem.setExpireTime(orderItemDto.getExpireTime());
        orderItem.setExpired(orderItemDto.getExpired());
        return orderItem;
    }
}
