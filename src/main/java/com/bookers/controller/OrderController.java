package com.bookers.controller;

import com.bookers.model.Address;
import com.bookers.model.Order;
import com.bookers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("customers/placeorder")
    public ResponseEntity<Order>placeOrderHandler(@RequestBody Address address){
        Order order = orderService.placeOrder(address);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("customers/cancelorder/{orderid}")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable("orderid") Integer orderId){
        Order order = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
