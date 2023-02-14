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

import java.util.List;
import java.util.Optional;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private UserDao userDao;


    @Override
    public Book addBookToCart(Book book, String key) throws AccessDenied, LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if (userCurrentSession == null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();
        if (user.getRole().equalsIgnoreCase("Buyer")) {
            Optional<Book> opt1 = bookDao.findById(book.getBookId());

            Cart cart = user.getCart();
            cart.getBook().add(book);
            cartDao.save(cart);
            return book;
        } else {
            throw new AccessDenied("Not Authorized");
        }
    }

    @Override
    public String removeBookFromCart(Book book, String key) throws AccessDenied, LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if (userCurrentSession == null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        int bookId = book.getBookId();

          User user = opt.get();

          int cartId = user.getCart().getCartId();

          Optional<Cart> opt2 = cartDao.findById(cartId);

          Cart cart = opt2.get();

          List<Book> books = cart.getBook();
        books.stream().filter(book1 ->book1.getBookId().equals(bookId)).findFirst().ifPresent(books::remove);
       String message = "Book removed from cart successfully";
       cartDao.save(cart);
        return message;
    }

    @Override
    public List<Book> getBooksInCart(String key) throws LoginException, BookException, AccessDenied {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if (userCurrentSession == null) throw new LoginException("Please Login");

        int userId = userCurrentSession.getUserId();

        Optional<User> opt =  userDao.findById(userId);

        User user = opt.get();

        List<Book> cartBooks = user.getCart().getBook();

        if(cartBooks.isEmpty()) throw new BookException("Cart is empty");

        return cartBooks;

    }

}

