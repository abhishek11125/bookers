package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.model.Book;
import com.bookers.model.Cart;
import com.bookers.model.User;
import com.bookers.model.UserCurrentSession;
import com.bookers.repository.BookDao;
import com.bookers.repository.CartDao;
import com.bookers.repository.UserDao;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDao cartDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private UserDao userDao;


    @Override
    public String addBookToCart(Book book, String key) throws BookException, AccessDenied, LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt= userDao.findById(userCurrentSession.getUserId());

        Optional<Book> opt1 = bookDao.findById(book.getBookId());

        Cart cart = new Cart();
        Book book1 = opt1.get();

        book1.setCart(cart);

        User user = opt.get();
        cart.getBooks().add(book1);

        user.setCart(cart);
        cart.setUser(user);

        cartDao.save(cart);
        bookDao.save(book1);
        return "Book successfully added to cart";
    }
}
