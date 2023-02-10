package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.model.Book;
import com.bookers.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;

public interface CartService {
    public Book addBookToCart(Book book, String key)throws AccessDenied, LoginException;

    public String removeBookFromCart(Book book,String key)throws AccessDenied,LoginException;
}
