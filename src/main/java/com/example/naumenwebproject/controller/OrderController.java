package com.example.naumenwebproject.controller;

import com.example.naumenwebproject.dto.OrderDto;
import com.example.naumenwebproject.exception.OrderItemNotFoundException;
import com.example.naumenwebproject.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@RequestParam Long orderId) {
        try {
            OrderDto orderDto = orderService.getOrder(orderId);
            return ResponseEntity.ok(orderDto);
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping
    public ResponseEntity<String> createOrder() {
        orderService.createOrder();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/add/{orderItemId}")
    public ResponseEntity<String> addOrderItemToOrder(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
    ) {
        if (orderService.checkOrderIsPaid(orderId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot add items to a paid order");
        }

        orderService.addOrderItemToOrder(orderId, orderItemId);
        return ResponseEntity.ok("Order item added successfully");
    }

    @DeleteMapping("/{orderId}/delete/{orderItemId}")
    public ResponseEntity<String> deleteOrderItemFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
    ) {
        if (orderService.checkOrderIsPaid(orderId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete items from a paid order");
        }

        orderService.deleteOrderItemFromOrder(orderId, orderItemId);
        return ResponseEntity.ok("Order item deleted successfully");
    }

    @PostMapping("/{orderId}/paid")
    public ResponseEntity<String> markOrderAsPaid(@PathVariable Long orderId) {
        orderService.markOrderAsPaid(orderId);
        return ResponseEntity.ok("Order marked as paid");
    }

    @DeleteMapping("/{orderId}/deleteOrder")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        if (orderService.checkOrderIsPaid(orderId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete a paid order");
        }

        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }

}

