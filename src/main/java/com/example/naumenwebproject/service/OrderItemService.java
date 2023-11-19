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
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    public void createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = this.orderItemMapper.dtoToOrderItem(orderItemDto);
        this.orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        if (!this.orderItemRepository.existsById(orderItemId)) {
            throw new OrderItemNotFoundException("OrderItem with ID " + orderItemId + " not found");
        } else {
            this.orderItemRepository.deleteById(orderItemId);
        }
    }

    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream().map(orderItemMapper::orderItemToDto).collect(Collectors.toList());
    }

    public OrderItemDto getOrderItem(Long orderItemId) {
        Optional<OrderItem> optionalOrderItem = this.orderItemRepository.findById(orderItemId);
        if (optionalOrderItem.isPresent()) {
            return this.orderItemMapper.orderItemToDto(optionalOrderItem.get());
        } else {
            throw new OrderItemNotFoundException("OrderItem with ID " + orderItemId + " not found");
        }
    }

    public void markOrderAsExpired(Long orderItemId) {
        OrderItem orderItem = (OrderItem)this.orderItemRepository.findById(orderItemId).orElseThrow(() -> {
            return new OrderItemNotFoundException("OrderItem not found");
        });
        if (LocalDateTime.now().isAfter(orderItem.getExpireTime())) {
            orderItem.setExpired(true);
            this.orderItemRepository.save(orderItem);
        }

    }
}
