package com.bookers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
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
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Book> books = new ArrayList<>();

}
