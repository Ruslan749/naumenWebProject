package com.example.naumenwebproject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems;

    @Column(name = "date_order")
    private LocalDateTime date;

    @Column(name = "paid")
    private Boolean paid;

}
