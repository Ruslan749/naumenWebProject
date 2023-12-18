package com.example.naumenwebproject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> orderItems;

    @Column(name = "date_order")
    private LocalDateTime date;

    @Column(name = "paid")
    private Boolean paid;

    // нужно для уменьшения запросов БД при проверке всех машин на "просроченность"
    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
