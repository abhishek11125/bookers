package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.model.Book;
import com.bookers.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CartService {
    public Book addBookToCart(Book book, Integer customerId);

    public String  removeBookFromCart(Book book,Integer customerId);

    public List<Book> getBooksInCart(Integer customerId)throws BookException;
}
