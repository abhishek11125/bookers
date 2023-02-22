package com.bookers.controller;

import com.bookers.model.Book;
import com.bookers.service.BookService;
import com.bookers.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    @PostMapping("author/addbook")
    public ResponseEntity<Book> addBookHandler(@Valid @RequestBody Book book){
        Book book1 =  bookService.addBook(book);
        return new ResponseEntity<>(book1, HttpStatus.ACCEPTED);
    }
    @GetMapping("customers/bookbytitle/{title}")
    public ResponseEntity<List<Book>> getBookByTitleHandler(@PathVariable String title){
        List<Book> books = bookService.getBookByTitle(title);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("customers/bookbyauthor/{name}")
    public ResponseEntity<List<Book>> getBookByAuthorHandler(@PathVariable String name){
        List<Book> books = bookService.getBookByAuthorName(name);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("customers/allbooks")
    public ResponseEntity<List<Book>> getAllBooksHandler(){
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("customers/bookbylang/{lang}")
    public ResponseEntity<List<Book>> getBookByLanguageHandler(@PathVariable("lang") String language){
        List<Book> books = bookService.getBookByLanguage(language);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @DeleteMapping("author/removebook/{bookid}")
    public ResponseEntity<Book> removeBookHandler(@PathVariable("bookid") Integer bookId){

        Book book = bookService.removeBook(bookId);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @PostMapping("customers/addtocart")
    public ResponseEntity<Book> addBookToCartHandler(@RequestBody Book book){
       Book book1 = cartService.addBookToCart(book);

       return new ResponseEntity<>(book1,HttpStatus.OK);
    }

    @DeleteMapping("customers/removefromcart")
    public ResponseEntity<String> removeFromCartHandler(@RequestBody Book book){
       String message =  cartService.removeBookFromCart(book);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @GetMapping("customers/cartbooks")
    public ResponseEntity<List<Book>> getBooksFromCartHandler(){
          List<Book> cartBooks =   cartService.getBooksInCart();
          return new ResponseEntity<>(cartBooks,HttpStatus.OK);
    }
}
