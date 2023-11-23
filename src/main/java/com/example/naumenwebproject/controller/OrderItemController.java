package com.example.naumenwebproject.controller;

import com.example.naumenwebproject.dto.OrderItemDto;
import com.example.naumenwebproject.exception.OrderItemNotFoundException;
import com.example.naumenwebproject.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
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
    public ResponseEntity<Void> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        orderItemService.createOrderItem(orderItemDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItemDto> orderItems = this.orderItemService.getAllOrderItems();
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

    @Async
    @Scheduled(fixedRate = 60000)
    public void checkForExpiredOrders() {
        log.info("Checking for expired orders...");

        List<OrderItemDto> orderItems = orderItemService.getAllOrderItems();

        for (OrderItemDto orderItem : orderItems) {
            orderItemService.markOrderAsExpired(orderItem.getId());
        }
    }
}