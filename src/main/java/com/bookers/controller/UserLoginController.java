package com.bookers.controller;

import com.bookers.model.UserLoginDTO;
import com.bookers.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;
    @PostMapping("/login")
    public ResponseEntity<String> userLoginHandler(@RequestBody UserLoginDTO loginDTO){
           String key =  userLoginService.logIntoAccount(loginDTO);

           return new ResponseEntity<>(key, HttpStatus.OK);
    }
    @PostMapping("/logout/{key}")
    public ResponseEntity<String> userLogOutHandler(@PathVariable("key") String key){
        String message =  userLoginService.logOutFromAccount(key);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
