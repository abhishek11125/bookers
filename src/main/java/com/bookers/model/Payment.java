package com.bookers.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "Please provide quantity to be purchased")
    @Min(value = 0,message = "Quantity can not be negative")
    private Integer quantity;

    @OneToMany(mappedBy = "payment",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

}
