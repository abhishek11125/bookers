package com.bookers.service;

import com.bookers.exception.BookException;
import com.bookers.model.Book;

import java.util.List;

public interface BookService {
    public Book addBook(Book book);

    public List<Book> getBookByTitle(String title) throws BookException;

    public List<Book> getBookByAuthorName(String name) throws BookException;

    public List<Book> getAllBooks()throws BookException;

    public Book removeBook(Integer bookId)throws BookException;

    public List<Book> getBookByLanguage(String language) throws BookException;

}
