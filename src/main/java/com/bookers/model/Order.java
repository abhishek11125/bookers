package com.bookers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Address address;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Book> books = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;
}
