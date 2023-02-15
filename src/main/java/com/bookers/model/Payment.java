package com.bookers.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
    @NotBlank
    private String paymentMethod;
    @NotBlank(message = "Please provide card holder name")
    private String cardHolderName;
    @CreditCardNumber
    private String cardNumber;
    @Min(value = 1,message = "CVV can not be blank")
    @Max(value = 3,message = "Please enter correct CVV")
    private Integer cvv;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

}
