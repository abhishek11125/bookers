package com.bookers.model;

import lombok.Data;

import javax.persistence.*;
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
    private LocalDateTime timeStamp;
    private String orderStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne
    private Payment payment;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();
}
