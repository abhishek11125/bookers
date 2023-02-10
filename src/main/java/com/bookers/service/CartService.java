package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.model.Book;
import com.bookers.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;

public interface CartService {
    public String addBookToCart(Book book, String key)throws BookException, AccessDenied, LoginException;
}
