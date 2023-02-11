package com.bookers.controller;

import com.bookers.model.Book;
import com.bookers.service.BookService;
import com.bookers.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    @PostMapping("/addbook/{key}")
    public ResponseEntity<Book> addBookHandler(@Valid @RequestBody Book book , @PathVariable("key") String key){
        Book book1 =  bookService.addBook(book, key);
        return new ResponseEntity<>(book1, HttpStatus.ACCEPTED);
    }
    @GetMapping("/bookbytitle/{title}/{key}")
    public ResponseEntity<List<Book>> getBookByTitleHandler(@PathVariable String title, @PathVariable String key){
        List<Book> books = bookService.getBookByTitle(title,key);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/bookbyauthor/{name}/{key}")
    public ResponseEntity<List<Book>> getBookByAuthorHandler(@PathVariable String name, @PathVariable String key){
        List<Book> books = bookService.getBookByAuthorName(name,key);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("/allbooks/{key}")
    public ResponseEntity<List<Book>> getAllBooksHandler( @PathVariable String key){
        List<Book> books = bookService.getAllBooks(key);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("/bookbylang/{lang}/{key}")
    public ResponseEntity<List<Book>> getBookByLanguageHandler(@PathVariable("lang") String language,@PathVariable("key") String key){
        List<Book> books = bookService.getBookByLanguage(language, key);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @DeleteMapping("/removebook/{bookid}/{key}")
    public ResponseEntity<Book> removeBookHandler(@PathVariable("bookid") Integer bookId,@PathVariable("key") String key){

        Book book = bookService.removeBook(bookId,key);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @PostMapping("/addtocart/{key}")
    public ResponseEntity<Book> addBookToCartHandler(@RequestBody Book book,@PathVariable("key") String key){
       Book book1 = cartService.addBookToCart(book,key);

       return new ResponseEntity<>(book1,HttpStatus.OK);
    }

    @DeleteMapping("/removefromcart/{key}")
    public ResponseEntity<String> removeFromCartHandler(@RequestBody Book book,@PathVariable("key") String key){
       String message =  cartService.removeBookFromCart(book,key);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
