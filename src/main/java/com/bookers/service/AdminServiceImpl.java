package com.bookers.service;

import com.bookers.exception.AuthorException;
import com.bookers.exception.CustomerException;
import com.bookers.model.Customer;
import com.bookers.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CustomerDao customerDao;
    @Override
    public List<Customer> getAllAuthors() throws AuthorException {
        List<Customer> customers = customerDao.findAll();
        List<Customer> authors = new ArrayList<>();
        for(Customer c:customers){
            if(c.getRole().equals("ROLE_AUTHOR")){
                authors.add(c);
            }
        }
        return authors;
    }

    @Override
    public List<Customer> getAllUsers() throws CustomerException {
        List<Customer> customers = customerDao.findAll();
        List<Customer> users = new ArrayList<>();
        for(Customer c:customers){
            if(c.getRole().equals("ROLE_USER")){
                users.add(c);
            }
        }
        return users;
    }
}
