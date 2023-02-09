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

    public List<Book> getAllBooks(String key)throws BookException,LoginException;

    public User updateMobile(String mobile, String key)throws LoginException;

    public User updatePassword(String email, String newPass, String key);

    public User getProfile(String key) throws LoginException;

    public Book removeBook(Integer bookId, String key)throws LoginException,BookException,AccessDenied;
}
