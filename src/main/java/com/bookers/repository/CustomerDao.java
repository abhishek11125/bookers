package com.bookers.repository;

import com.bookers.exception.CustomerException;
import com.bookers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {

    public Customer findByEmail(String email)throws CustomerException;

}


