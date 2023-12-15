package com.example.naumenwebproject.controller;

import com.example.naumenwebproject.dto.OrderItemDto;
import com.example.naumenwebproject.exception.OrderItemNotFoundException;
import com.example.naumenwebproject.model.OrderItem;
import com.example.naumenwebproject.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/api/order-items"})
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemService.createOrderItem(orderItemDto);
        return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItemDto> orderItems = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDto> getOrderItem(@PathVariable Long orderItemId) {
        try {
            OrderItemDto orderItemDto = orderItemService.getOrderItem(orderItemId);
            return ResponseEntity.ok(orderItemDto);
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Возможно не нужен
    @PutMapping("/{orderItemId}/set-expire")
    public ResponseEntity<String> setOrderItemIsExpired(@PathVariable Long orderItemId) {
        try {
            orderItemService.setOrderItemIsExpired(orderItemId);
            return ResponseEntity.ok("Order item expiration status updated successfully");
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }



    // подумать над логикой проверки. Над оплатой
}