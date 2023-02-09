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

        if (userCurrentSession==null) throw new LoginException("Please Login");
        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        if(user.getRole().equalsIgnoreCase("Admin")){
               List<User> users =  userDao.findAll();
               return users;
        }else throw new AccessDenied("Not Authorized");

    }

    @Override
    public Book addBook(Book book, String key) throws LoginException, AccessDenied {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

       if(userCurrentSession==null) throw new LoginException("Please Login");
       Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

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
        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());


           List<Book> books =  bookDao.findByTitle(title);

           if(books.isEmpty()) throw new BookException("Book not found with title: "+title);

           return books;
    }

    @Override
    public List<Book> getBookByAuthorName(String name, String key) throws LoginException, AuthorException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

         List<Book> books =  bookDao.findByAuthorName(name);

         if(books.isEmpty()) throw new AuthorException("Book not found with author name "+name);
         return books;
    }

    @Override
    public List<Book> getAllBooks(String key) throws BookException, LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");
        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

          List<Book> books =  bookDao.findAll();
          if(books.isEmpty()) throw new BookException("No any book found");
          return books;
    }

    @Override
    public User updateMobile(String mobile, String key) throws LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

       Optional<User> user = userDao.findById(userCurrentSession.getUserId());

       User user1 = opt.get();

       user1.setMobile(mobile);

       userDao.save(user1);
       return  user1;
    }

    @Override
    public User updatePassword(String email, String newPass, String key) {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");
        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());


        User user = userDao.findByEmail(email);
        user.setPassword(newPass);
        userDao.save(user);

        return user;
    }

    @Override
    public User getProfile(String key) throws LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
            if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        return user;
    }

}
