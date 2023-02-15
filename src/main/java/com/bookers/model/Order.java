package com.bookers.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orderDetails")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @NotNull
    private LocalDate date;
    @NotBlank
    private String orderStatus;

    private double totalAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();

    @OneToOne
    private Payment payment;
}
