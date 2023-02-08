package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.Book;
import com.bookers.model.UserCurrentSession;
import com.bookers.repository.BookDao;
import com.bookers.repository.UserDao;
import com.bookers.model.User;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Override
    public User registerUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
       User user =userDao.findByEmail(email);
       if(user==null) throw new UserException("User not found with email"+email);
       else return user;
    }

    @Override
    public List<User> getAllUsers(String key) throws LoginException, AccessDenied {
        UserCurrentSession userCurrentSession =  userSessionDao.findByUid(key);

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        if (opt.isEmpty()) throw new LoginException("Please Login");
        User user = opt.get();

        if(user.getRole().equalsIgnoreCase("Admin")){
               List<User> users =  userDao.findAll();
               return users;
        }else throw new AccessDenied("Not Authorized");

    }

    @Override
    public Book addBook(Book book, String key) throws LoginException, AccessDenied {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

       Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

       if(opt.isEmpty()) throw new LoginException("Please Login");
       User user = opt.get();

       if(user.getRole().equalsIgnoreCase("Author")){
               Book book1 = bookDao.save(book);
               book1.setAuthorName(user.getName());
               return book1;
       }
       throw new AccessDenied("Not Authorized");
    }

    @Override
    public List<Book> getBookByTitle(String title, String key) throws LoginException, BookException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        if(opt.isEmpty()) throw new LoginException("Please Login");

           List<Book> books =  bookDao.findByTitle(title);

           if(books.isEmpty()) throw new BookException("Book not found with title: "+title);

           return books;
    }

    @Override
    public List<Book> getBookByAuthorName(String name, String key) throws LoginException, AuthorException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        if(opt.isEmpty()) throw new LoginException("Please Login");

         List<Book> books =  bookDao.findByAuthorName(name);

         if(books.isEmpty()) throw new AuthorException("Book not found with author name "+name);
         return books;
    }

}
