package com.bookers.service;

import com.bookers.exception.BookException;
import com.bookers.exception.LoginException;
import com.bookers.exception.OrderException;
import com.bookers.model.Address;
import com.bookers.model.Book;
import com.bookers.model.Order;

import java.util.List;

public interface OrderService {
    public Order placeOrder(Address address)throws OrderException;

    public Order cancelOrder(Integer orderId)throws OrderException;

}
