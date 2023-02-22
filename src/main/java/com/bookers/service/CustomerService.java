package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.Customer;
import jakarta.transaction.Transactional;

import java.util.List;
public interface CustomerService {

    public Customer registerUser(Customer customer);

    public Customer getUserByEmail(String email)throws CustomerException;

    public List<Customer> getAllUsers();


    public Customer updatePassword(String email, String newPass)throws CustomerException;

    Customer updateMobile(String mobile, String email);



//    public Customer getProfile(String key) throws LoginException;

   }
