package com.bookers.repository;

import com.bookers.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {
}
