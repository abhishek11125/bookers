package com.bookers.service;

import com.bookers.exception.AuthorException;
import com.bookers.exception.CustomerException;
import com.bookers.model.Customer;
import com.bookers.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AdminService {

   public List<Customer> getAllAuthors()throws AuthorException;

   public List<Customer> getAllUsers()throws CustomerException;
}
