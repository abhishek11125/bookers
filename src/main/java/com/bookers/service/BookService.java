package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.AuthorException;
import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.model.Book;

import java.util.List;

public interface BookService {
    public Book addBook(Book book, String key)throws LoginException, AccessDenied;

    public List<Book> getBookByTitle(String title, String key) throws LoginException, BookException;

    public List<Book> getBookByAuthorName(String name, String key) throws LoginException, AuthorException;

    public List<Book> getAllBooks(String key)throws BookException,LoginException;

    public Book removeBook(Integer bookId, String key)throws LoginException,BookException,AccessDenied;

    public List<Book> getBookByLanguage(String language, String key) throws LoginException,BookException;

}
