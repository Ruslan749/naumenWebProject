package com.example.naumenwebproject.model;

import com.example.naumenwebproject.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "имя не должно быть пустым")
    @Size(min = 2,max = 100,message = "имя должно быть от 2 до 100 символов")
    @Column(name = "username")
    private String username;

//    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String roles;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Person(String username, String phone) {
        this.username = username;
        this.phone = phone;
    }

}
