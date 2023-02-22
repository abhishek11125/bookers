package com.bookers.service;

import com.bookers.exception.BookException;
import com.bookers.model.Book;
import com.bookers.repository.BookDao;
import com.bookers.repository.CustomerDao;
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

    @Override
    public Book addBook(Book book){
       return bookDao.save(book);
    }

    @Override
    public List<Book> getBookByTitle(String title) throws BookException {
       List<Book> books = bookDao.findByTitle(title);
       if(books.isEmpty())throw new BookException("No any book found with title as "+title);
       return books;
    }

    @Override
    public List<Book> getBookByAuthorName(String name) throws BookException {
       List<Book> books = bookDao.findByAuthorName(name);
       if(books.isEmpty())throw new BookException("Book not present with author name "+name);
       return books;
    }

    @Override
    public List<Book> getAllBooks() throws BookException{
        List<Book> books = bookDao.findAll();

        if(books.isEmpty())throw new BookException("No any book is present for sale");
        return books;
    }

    @Override
    public Book removeBook(Integer bookId) throws BookException {
           Optional<Book> opt =  bookDao.findById(bookId);
           if(opt.isPresent()){
               Book book = opt.get();
               bookDao.delete(book);
               return book;
           }else throw new BookException("Book not found with book id"+ bookId);
    }

    @Override
    public List<Book> getBookByLanguage(String language)throws BookException {
      List<Book> books = bookDao.findByLanguage(language);
      if(books.isEmpty())throw new BookException("Book not present currently with language "+language);
      return books;

    }
}
