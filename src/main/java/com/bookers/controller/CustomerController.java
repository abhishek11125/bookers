package com.bookers.controller;

import com.bookers.model.Customer;
import com.bookers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUserHandler(@Valid @RequestBody Customer customer){
       customer.setRole("ROLE_"+customer.getRole().toUpperCase());
       customer.setPassword(passwordEncoder.encode(customer.getPassword()));
       Customer customer1 = customerService.registerUser(customer);
        return new ResponseEntity<>(customer1, HttpStatus.ACCEPTED);
    }
    @GetMapping("/customers/{email}")
    public ResponseEntity<Customer> getUserByEmailHandler(@PathVariable("email") String email){
           Customer customer = customerService.getUserByEmail(email);

           return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllUsersHandler(){
        List<Customer> customers =  customerService.getAllUsers();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    @PutMapping("customers/updatemobile/{mobile}/{email}")
    public ResponseEntity<Customer> updateMobileHandler(@Valid @PathVariable("mobile") String mobile,@PathVariable("email")String email){
        Customer customer = customerService.updateMobile(mobile,email);

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @PutMapping("customers/updatepassword/{email}/{newpass}")
    public ResponseEntity<Customer> updatePasswordHandler(@Valid @PathVariable("email") String email, @PathVariable("newpass") String newPassword){
        Customer customer = customerService.updatePassword(email,newPassword);

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("customers/profile")
    public ResponseEntity<Customer> getProfileHandler(){
        Customer customer =  customerService.getProfile();
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

}
