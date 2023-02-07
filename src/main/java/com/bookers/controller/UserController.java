package com.bookers.controller;

import com.bookers.exception.UserException;
import com.bookers.model.User;
import com.bookers.repository.UserDao;
import com.bookers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUserHandler(@RequestBody  User user){
       User user1 = userService.registerUser(user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserByEmailHandler(@PathVariable("email") String email){
           User user= userService.getUserByEmail(email);

           return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/users/{key}")
    public ResponseEntity<List<User>> getAllUsersHandler(@PathVariable("key") String key){
        List<User> users =  userService.getAllUsers(key);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
