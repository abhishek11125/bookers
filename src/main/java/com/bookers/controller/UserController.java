package com.bookers.controller;

import com.bookers.exception.UserException;
import com.bookers.model.Book;
import com.bookers.model.Order;
import com.bookers.model.User;
import com.bookers.repository.UserDao;
import com.bookers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUserHandler(@Valid @RequestBody  User user){
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
    @PutMapping("/updatemobile/{mobile}/{key}")
    public ResponseEntity<User> updateMobileHandler(@Valid @PathVariable("mobile") String mobile,@PathVariable("key") String key){
        User user = userService.updateMobile(mobile,key);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/updatepassword/{email}/{newpass}/{key}")
    public ResponseEntity<User> updatePasswordHandler(@Valid @PathVariable("email") String email,@PathVariable("newpass") String newPassword, @PathVariable("key") String  key){
        User user = userService.updatePassword(email,newPassword,key);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/profile/{key}")
    public ResponseEntity<User> getProfileHandler(@PathVariable("key") String key){
        User user =  userService.getProfile(key);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/orders/{key}")
    public ResponseEntity<List<Order>> getPlacedOrdersHandler(@PathVariable("key")String key){
        List<Order> orders = userService.getOrderHistory(key);

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @DeleteMapping("cancelorder/{orderid}/{key}")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable("orderid") Integer orderId,@PathVariable("key") String key){
        Order order = userService.cancelOrder(orderId, key);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

}
