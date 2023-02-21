package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.AuthorException;
import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.model.Book;
import com.bookers.model.Customer;
import com.bookers.model.UserCurrentSession;
import com.bookers.repository.BookDao;
import com.bookers.repository.CustomerDao;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private UserSessionDao userSessionDao;

    @Override
    public Book addBook(Book book, String key) throws LoginException, AccessDenied {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");
        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        Customer customer = opt.get();

        if(customer.getRole().equalsIgnoreCase("Author")){
            book.setAuthorName(customer.getName());
            Book book1 = bookDao.save(book);
            return book1;
        }
        throw new AccessDenied("Not Authorized");
    }

    @Override
    public List<Book> getBookByTitle(String title, String key) throws LoginException, BookException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());


        List<Book> books =  bookDao.findByTitle(title);

        if(books.isEmpty()) throw new BookException("Book not found with title: "+title);

        return books;
    }

    @Override
    public List<Book> getBookByAuthorName(String name, String key) throws LoginException, AuthorException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        List<Book> books =  bookDao.findByAuthorName(name);

        if(books.isEmpty()) throw new AuthorException("Book not found with author name "+name);
        return books;
    }

    @Override
    public List<Book> getAllBooks(String key) throws BookException, LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");
        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        List<Book> books =  bookDao.findAll();
        if(books.isEmpty()) throw new BookException("No any book found");
        return books;
    }

    @Override
    public Book removeBook(Integer bookId, String key) throws LoginException, BookException, AccessDenied {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        Customer customer = opt.get();

        if(customer.getRole().equalsIgnoreCase("Author")){
            Optional<Book> opt1 = bookDao.findById(bookId);
            if(opt1.isEmpty()) throw new BookException("Book not found with book id "+bookId);
            Book book = opt1.get();

            bookDao.delete(book);
            return book;
        }else throw new AccessDenied("Not Authorized");
    }

    @Override
    public List<Book> getBookByLanguage(String language, String key) throws LoginException, BookException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
        if(userCurrentSession==null) throw new LoginException("Please Login");

        List<Book> books =  bookDao.findByLanguage(language);
        if (books.isEmpty())throw new BookException("Book not found with language "+language);

        return books;

    }
}
