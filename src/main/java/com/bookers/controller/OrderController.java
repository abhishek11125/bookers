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

    @PostMapping("placeorder/{key}")
    public ResponseEntity<Order>placeOrderHandler(@RequestBody Address address, @PathVariable("key") String  key){
        Order order = orderService.placeOrder(address,key);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("cancelorder/{orderid}/{key}")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable("orderid") Integer orderId,@PathVariable("key") String key){
        Order order = orderService.cancelOrder(orderId, key);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
