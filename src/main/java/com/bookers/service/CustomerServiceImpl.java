package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.*;
import com.bookers.repository.BookDao;
import com.bookers.repository.OrderDao;
import com.bookers.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;
    @Override
    public Customer registerUser(Customer customer) {
            Cart cart = new Cart();
            customer.setCart(cart);
            cart.setCustomer(customer);
        return customerDao.save(customer);
    }

    @Override
    public Customer getUserByEmail(String email) throws CustomerException {
       Customer customer = customerDao.findByEmail(email);
       if(customer ==null) throw new CustomerException("User not found with email "+email);
       else return customer;
    }

    @Override
    public List<Customer> getAllUsers(){
       List<Customer> customers = customerDao.findAll();
       return customers;
    }

    @Override
    public Customer updateMobile(String mobile, String email){

        Customer customer = customerDao.findByEmail(email);

        customer.setMobile(mobile);

       Customer customer1 = customerDao.save(customer);

       return customer1;
    }

    @Override
    public Customer updatePassword(String email, String newPass)throws CustomerException {
        Customer customer = customerDao.findByEmail(email);

        if(customer==null)throw new CustomerException("Customer not found with email "+email);

        customer.setPassword(newPass);

        Customer customer1 = customerDao.save(customer);

        return customer;
    }

    @Override
    public Customer getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer customer = customerDao.findByEmail(authentication.getName());

        return customer;
    }

}
