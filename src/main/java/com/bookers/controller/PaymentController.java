package com.bookers.controller;

import com.bookers.model.Payment;
import com.bookers.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("customers/payment")
    public ResponseEntity<Integer> proceedToPaymentHandler(@RequestBody Payment payment){
       Integer paymentId = paymentService.proceedToPayment(payment);

       return new ResponseEntity<>(paymentId, HttpStatus.OK);
    }
}
