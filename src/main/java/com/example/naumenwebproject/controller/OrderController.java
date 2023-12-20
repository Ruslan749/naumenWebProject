package com.example.naumenwebproject.controller;

import com.example.naumenwebproject.dto.OrderDto;
import com.example.naumenwebproject.exception.OrderItemNotFoundException;
import com.example.naumenwebproject.exception.OrderNotFoundException;
import com.example.naumenwebproject.model.Order;
import com.example.naumenwebproject.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder() {
        orderService.createOrder();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        try {
            OrderDto orderDto = orderService.getOrder(orderId);
            return ResponseEntity.ok(orderDto);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        return ResponseEntity.ok(orderDtos);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        try {
            if (orderService.checkOrderIsPaid(orderId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete a paid order");
            }
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (OrderItemNotFoundException | OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderId}/delete/{orderItemId}")
    public ResponseEntity<String> deleteOrderItemFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
    ) {
        try {
            if (orderService.checkOrderIsPaid(orderId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete items from a paid order");
            }
            orderService.deleteOrderItemFromOrder(orderId, orderItemId);
            return ResponseEntity.ok("Order item deleted successfully");
        } catch (OrderItemNotFoundException | OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{orderId}/add/{orderItemId}")
    public ResponseEntity<String> setOrderItemToOrder(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
    ) {
        try {
            if (orderService.checkOrderIsPaid(orderId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot add items to a paid order");
            }
            orderService.setOrderItemToOrder(orderId, orderItemId);
            return ResponseEntity.ok("Order item added successfully");
        } catch (OrderItemNotFoundException | OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{orderId}/setPaid")
    public ResponseEntity<String> setOrderIsPaid(@PathVariable Long orderId) {
        try {
            orderService.setOrderIsPaid(orderId);
            return ResponseEntity.ok("Order has been paid successfully");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/checkUnpaid")
    public ResponseEntity<Boolean> checkUnpaidOrder() {
        Boolean hasUnpaidOrder = orderService.checkUnpaidOrder();
        return ResponseEntity.ok(hasUnpaidOrder);
    }

    @Async
    @Scheduled(fixedDelay = 5 * 60 * 1000)
    @PostMapping("/updateNotActive")
    public void updateOrderAsNotActive() {
        log.info("Checking");
        orderService.updateOrderAsNotActive();
    }
}

