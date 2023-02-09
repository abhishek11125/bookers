package com.bookers.controller;

import com.bookers.exception.UserException;
import com.bookers.model.Book;
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
    @PostMapping("/addbook/{key}")
    public ResponseEntity<Book> addBookHandler(@RequestBody Book book ,@PathVariable("key") String key){
           Book book1 =  userService.addBook(book, key);
           return new ResponseEntity<>(book1,HttpStatus.ACCEPTED);
    }
    @GetMapping("/bookbytitle/{title}/{key}")
    public ResponseEntity<List<Book>> getBookByTitleHandler(@PathVariable String title, @PathVariable String key){
        List<Book> books = userService.getBookByTitle(title,key);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/bookbyauthor/{name}/{key}")
    public ResponseEntity<List<Book>> getBookByAuthorHandler(@PathVariable String name, @PathVariable String key){
        List<Book> books = userService.getBookByAuthorName(name,key);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("/allbooks/{key}")
    public ResponseEntity<List<Book>> getAllBooksHandler( @PathVariable String key){
        List<Book> books = userService.getAllBooks(key);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @PutMapping("/updatemobile/{mobile}/{key}")
    public ResponseEntity<User> updateMobileHandler(@PathVariable("mobile") String mobile,@PathVariable("key") String key){
        User user = userService.updateMobile(mobile,key);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/updatepassword/{email}/{newpass}/{key}")
    public ResponseEntity<User> updatePasswordHandler(@PathVariable("email") String email,@PathVariable("newpass") String newPassword, @PathVariable("key") String  key){
        User user = userService.updatePassword(email,newPassword,key);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/profile/{key}")
    public ResponseEntity<User> getProfileHandler(@PathVariable("key") String key){
        User user =  userService.getProfile(key);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
