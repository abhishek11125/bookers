package com.bookers.service;

import com.bookers.exception.BookException;
import com.bookers.model.Book;

import java.util.List;

public interface CartService {
    public Book addBookToCart(Book book);

    public String  removeBookFromCart(Book book);

    public List<Book> getBooksInCart()throws BookException;
}
