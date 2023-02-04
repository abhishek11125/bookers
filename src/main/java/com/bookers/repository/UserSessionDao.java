package com.bookers.repository;

import com.bookers.model.UserCurrentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionDao extends JpaRepository<UserCurrentSession,Integer> {
    public UserCurrentSession findByUserKey(String key);

}
