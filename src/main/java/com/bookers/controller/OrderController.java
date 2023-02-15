package com.bookers.controller;

import com.bookers.model.Address;
import com.bookers.model.Order;
import com.bookers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/placeorder/{key}")
    public ResponseEntity<Order>placeOrderHandler(@RequestBody Address address, @PathVariable("key") String  key){
        Order order = orderService.placeOrder(address,key);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }
}
