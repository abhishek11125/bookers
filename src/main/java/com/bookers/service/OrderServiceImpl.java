package com.bookers.service;

import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.exception.OrderException;
import com.bookers.model.*;
import com.bookers.repository.CartDao;
import com.bookers.repository.OrderDao;
import com.bookers.repository.UserDao;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private CartDao cartDao;
    @Override
    public Order placeOrder(Address address, String key)throws LoginException, BookException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if (userCurrentSession == null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        List<Book> books1 = user.getCart().getBook();
        if(books1.isEmpty())throw new BookException("Cart is empty");

        Order order = new Order();
        double finalPrice = 0.0;
        for(Book i:books1){
            i.setOrder(order);
            finalPrice = finalPrice+i.getPrice();
            order.getBooks().add(i);
        }
        books1.removeAll(books1);
        order.setOrderStatus("Processing");
        order.setDate(LocalDate.now());
        order.setAddress(address);
        order.setTotalAmount(finalPrice);
        order.setUser(user);
        address.getOrders().add(order);
        address.setUser(user);
        user.setOrder(order);

        return orderDao.save(order);
    }

    @Override
    public Order cancelOrder(Integer orderId, String key) throws LoginException, OrderException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        Optional<Order> opt2 = orderDao.findById(orderId);

        if (opt2.isEmpty())throw new OrderException("Order not found with order id "+orderId);

        Order order = opt2.get();

        Order order1 = user.getOrder();
        order1.setOrderStatus("Cancelled");

        orderDao.delete(order);

        return order;
    }
}
