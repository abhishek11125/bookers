package com.bookers.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    @JsonIgnore(value = false)
    private LocalDateTime timeStamp;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    @NotBlank(message = "Please provide card holder name")
    private String cardHolderName;
    @CreditCardNumber
    private String cardNumber;
    @Min(value = 1,message = "CVV can not be blank")
    @Max(value = 3,message = "Please enter correct CVV")
    private Integer cvv;
    @JsonIgnore(value = false)
    private double paymentAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Order order;

}
