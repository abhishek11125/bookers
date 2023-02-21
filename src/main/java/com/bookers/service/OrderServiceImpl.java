package com.bookers.service;

import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.exception.OrderException;
import com.bookers.model.*;
import com.bookers.repository.CartDao;
import com.bookers.repository.OrderDao;
import com.bookers.repository.CustomerDao;
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
    private CustomerDao customerDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private CartDao cartDao;
    @Override
    public Order placeOrder(Address address, String key)throws LoginException, BookException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if (userCurrentSession == null) throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        Customer customer = opt.get();

        List<Book> books1 = customer.getCart().getBook();
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
        order.setCustomer(customer);
        address.getOrders().add(order);
        address.setCustomer(customer);
        customer.setOrder(order);

        return orderDao.save(order);
    }

    @Override
    public Order cancelOrder(Integer orderId, String key) throws LoginException, OrderException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        Customer customer = opt.get();

        Optional<Order> opt2 = orderDao.findById(orderId);

        if (opt2.isEmpty())throw new OrderException("Order not found with order id "+orderId);

        Order order = opt2.get();

        Order order1 = customer.getOrder();
        order1.setOrderStatus("Cancelled");

        orderDao.delete(order);

        return order;
    }
}
