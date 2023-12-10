package com.example.naumenwebproject.service;

import com.example.naumenwebproject.dto.OrderDto;
import com.example.naumenwebproject.exception.OrderItemNotFoundException;
import com.example.naumenwebproject.exception.OrderNotFoundException;
import com.example.naumenwebproject.mapper.OrderMapper;
import com.example.naumenwebproject.model.Order;
import com.example.naumenwebproject.model.OrderItem;
import com.example.naumenwebproject.repository.OrderItemRepository;
import com.example.naumenwebproject.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
    }

    public void createOrder() {
        Order order = new Order();
        order.setPaid(false);
        order.setDate(LocalDateTime.now());
        order.setActive(true);

        orderRepository.save(order);
    }

    public OrderDto getOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            return orderMapper.orderToDto(optionalOrder.get());
        } else {
            throw new OrderItemNotFoundException("OrderItem with ID " + orderId + " not found");
        }
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.orderToDtoList(orders);
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderItemNotFoundException("Order with ID " + orderId + " not found");
        } else {
            orderRepository.deleteById(orderId);
        }
        orderRepository.deleteById(orderId);
    }

    public void deleteOrderItemFromOrder(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));

        OrderItem orderItemToRemove = order.getOrderItems().stream()
                .filter(item -> item.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem with ID " + orderItemId + " not found in the Order"));

        order.getOrderItems().remove(orderItemToRemove);

        orderRepository.save(order);
    }

    public void setOrderItemToOrder(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem with ID " + orderItemId + " not found"));

        orderItem.setOrder(order);

        orderRepository.save(order);
    }

    public void setOrderIsPaid(Long orderId)   {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setPaid(true);

        orderRepository.save(order);
    }

    public Boolean checkOrderIsPaid(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return order.getPaid();
    }

    public void setOrderIsNotActive(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setActive(false);

        orderRepository.save(order);
    }

    public Boolean checkOrderIsActive(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return order.getActive();
    }


}
