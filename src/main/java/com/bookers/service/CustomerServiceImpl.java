package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.*;
import com.bookers.repository.BookDao;
import com.bookers.repository.OrderDao;
import com.bookers.repository.CustomerDao;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private OrderDao orderDao;
    @Override
    public Customer registerUser(Customer customer) {
        if(customer.getRole().equalsIgnoreCase("Buyer")) {
            Cart cart = new Cart();
            customer.setCart(cart);
            cart.setCustomer(customer);
        }
        return customerDao.save(customer);
    }

    @Override
    public Customer getUserByEmail(String email) throws CustomerException {
       Customer customer = customerDao.findByEmail(email);
       if(customer ==null) throw new CustomerException("User not found with email"+email);
       else return customer;
    }

    @Override
    public List<Customer> getAllUsers(String key) throws LoginException, AccessDenied {
        UserCurrentSession userCurrentSession =  userSessionDao.findByUid(key);

        if (userCurrentSession==null) throw new LoginException("Please Login");
        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        Customer customer = opt.get();

        if(customer.getRole().equalsIgnoreCase("Admin")){
               List<Customer> customers =  customerDao.findAll();
               return customers;
        }else throw new AccessDenied("Not Authorized");

    }

    @Override
    public Customer updateMobile(String mobile, String key) throws LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

       Optional<Customer> user = customerDao.findById(userCurrentSession.getUserId());

       Customer customer1 = opt.get();

       customer1.setMobile(mobile);

       customerDao.save(customer1);
       return customer1;
    }

    @Override
    public Customer updatePassword(String email, String newPass, String key) {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");
        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());


        Customer customer = customerDao.findByEmail(email);
        customer.setPassword(newPass);
        customerDao.save(customer);

        return customer;
    }

    @Override
    public Customer getProfile(String key) throws LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
            if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        Customer customer = opt.get();

        return customer;
    }

}
