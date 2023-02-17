package com.bookers.controller;

import com.bookers.model.Payment;
import com.bookers.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("payment/{key}")
    public ResponseEntity<Integer> proceedToPaymentHandler(@RequestBody Payment payment, @PathVariable("key")String key){
       Integer paymentId = paymentService.proceedToPayment(payment,key);

       return new ResponseEntity<>(paymentId, HttpStatus.OK);
    }
}
