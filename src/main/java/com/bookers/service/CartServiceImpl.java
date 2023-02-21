package com.bookers.service;

import com.bookers.exception.BookException;
import com.bookers.model.Book;
import com.bookers.model.Cart;
import com.bookers.model.Customer;
import com.bookers.repository.BookDao;
import com.bookers.repository.CartDao;
import com.bookers.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CustomerDao customerDao;


    @Override
    public Book addBookToCart(Book book,Integer customerId){

        Optional<Customer> opt = customerDao.findById(customerId);

        Customer customer = opt.get();

        Cart cart = customer.getCart();

        cart.getBook().add(book);
        cartDao.save(cart);
        return book;
    }

    @Override
    public String removeBookFromCart(Book book, Integer customerId){
        Optional<Customer> opt = customerDao.findById(customerId);
        Customer customer = opt.get();

        Cart cart = customer.getCart();
        List<Book> books = cart.getBook();
        Book book1 = null;
        for (Book b:books){
            if(b.getBookId()==book.getBookId()){
                book1 = b;
                break;
            }
        }
        books.remove(book1);
        cartDao.save(cart);
        return "book removed from cart successfully";
    }

    @Override
    public List<Book> getBooksInCart(Integer customerId) throws BookException{
        Optional<Customer> opt = customerDao.findById(customerId);

        Customer customer = opt.get();

        Cart cart = customer.getCart();

        List<Book> books = cart.getBook();
        if(books.isEmpty())throw new BookException("Cart is empty");

        return books;
    }

}

