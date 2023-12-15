package com.example.naumenwebproject.repository;

import com.example.naumenwebproject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem getOrderItemById(Long orderItemId);
}