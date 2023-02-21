package com.bookers.controller;

import com.bookers.model.Customer;
import com.bookers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUserHandler(@Valid @RequestBody Customer customer){
       Customer customer1 = customerService.registerUser(customer);
        return new ResponseEntity<>(customer1, HttpStatus.ACCEPTED);
    }
    @GetMapping("user/{email}")
    public ResponseEntity<Customer> getUserByEmailHandler(@PathVariable("email") String email){
           Customer customer = customerService.getUserByEmail(email);

           return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @GetMapping("users/{key}")
    public ResponseEntity<List<Customer>> getAllUsersHandler(@PathVariable("key") String key){
        List<Customer> customers =  customerService.getAllUsers(key);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @PutMapping("updatemobile/{mobile}/{key}")
    public ResponseEntity<Customer> updateMobileHandler(@Valid @PathVariable("mobile") String mobile, @PathVariable("key") String key){
        Customer customer = customerService.updateMobile(mobile,key);

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @PutMapping("updatepassword/{email}/{newpass}/{key}")
    public ResponseEntity<Customer> updatePasswordHandler(@Valid @PathVariable("email") String email, @PathVariable("newpass") String newPassword, @PathVariable("key") String  key){
        Customer customer = customerService.updatePassword(email,newPassword,key);

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("profile/{key}")
    public ResponseEntity<Customer> getProfileHandler(@PathVariable("key") String key){
        Customer customer =  customerService.getProfile(key);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

}
