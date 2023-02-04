package com.bookers.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    private LocalDateTime timeStamp;
    private boolean status;
    private Integer quantity;

    @OneToMany(mappedBy = "payment",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
