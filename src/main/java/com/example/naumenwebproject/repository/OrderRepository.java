package com.example.naumenwebproject.repository;

import com.example.naumenwebproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByPaidFalseAndPersonUsername(String username);

    List<Order> findByActiveTrueAndPaidTrue();
}