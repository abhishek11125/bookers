package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.Book;
import com.bookers.model.User;
import com.bookers.model.UserCurrentSession;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User getUserByEmail(String email)throws UserException;

    public List<User> getAllUsers(String key)throws LoginException, AccessDenied;

    public Book addBook(Book book,String key)throws LoginException, AccessDenied;

    public List<Book> getBookByTitle(String title, String key) throws LoginException, BookException;

    public List<Book> getBookByAuthorName(String name, String key) throws LoginException, AuthorException;

}
