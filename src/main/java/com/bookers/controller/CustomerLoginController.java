package com.bookers.controller;

import com.bookers.model.Customer;
import com.bookers.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerLoginController {
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/signIn")
    public ResponseEntity<Customer> userLoginHandler(Authentication auth){
        Customer customer = customerDao.findByEmail(auth.getName());

        return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
    }
//    @PostMapping("logout/{key}")
//    public ResponseEntity<String> userLogOutHandler(@PathVariable("key") String key){
//        String message =  userLoginService.logOutFromAccount(key);
//
//        return new ResponseEntity<>(message,HttpStatus.OK);
//    }
}
