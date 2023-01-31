package com.bookers.model;

import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

public class Payment {
    private Integer paymentId;
    private LocalDateTime timeStamp;
    private boolean status;
    private Integer quantity;
    @OneToOne
    private Book book;
    @OneToOne
    private User user;
}
