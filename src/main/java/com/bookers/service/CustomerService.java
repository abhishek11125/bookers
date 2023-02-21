package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer registerUser(Customer customer);

    public Customer getUserByEmail(String email)throws CustomerException;

    public List<Customer> getAllUsers(String key)throws LoginException, AccessDenied;

    public Customer updateMobile(String mobile, String key)throws LoginException;

    public Customer updatePassword(String email, String newPass, String key);

    public Customer getProfile(String key) throws LoginException;

   }
