package com.bookers.controller;

import com.bookers.model.Customer;
import com.bookers.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/authors")
    public ResponseEntity<List<Customer>> getAllAuthorsHandler(){
      List<Customer> authors =  adminService.getAllAuthors();
      return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<Customer>> getAllUsersHandler(){
        List<Customer> users =  adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
