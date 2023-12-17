package com.example.naumenwebproject.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "manufacture_year")
    private LocalDate manufactureYear;

    @Column(name = "image")
    private Byte[] image;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "car")
    private List<OrderItem> orderItems;

    // deleted flag на рассмотрение
}
