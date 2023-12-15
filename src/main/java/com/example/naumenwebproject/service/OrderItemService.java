package com.example.naumenwebproject.service;

import com.example.naumenwebproject.dto.OrderItemDto;
import com.example.naumenwebproject.exception.OrderItemNotFoundException;
import com.example.naumenwebproject.mapper.OrderItemMapper;
import com.example.naumenwebproject.model.OrderItem;
import com.example.naumenwebproject.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    public OrderItem createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemMapper.dtoToOrderItem(orderItemDto);

        orderItemRepository.save(orderItem);

        return orderItem;
    }

    public OrderItemDto getOrderItem(Long orderItemId) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        if (optionalOrderItem.isPresent()) {
            return orderItemMapper.orderItemToDto(optionalOrderItem.get());
        } else {
            throw new OrderItemNotFoundException("OrderItem with ID " + orderItemId + " not found");
        }
    }

    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItemMapper.orderItemsToDtoList(orderItems);
    }

    public void setOrderItemIsExpired(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found"));
        if (LocalDateTime.now().isAfter(orderItem.getExpireTime())) {
            orderItemRepository.save(orderItem);
        }
    }
}
