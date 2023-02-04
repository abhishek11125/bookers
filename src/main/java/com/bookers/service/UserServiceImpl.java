package com.bookers.service;

import com.bookers.repository.UserDao;
import com.bookers.exception.UserException;
import com.bookers.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public User registerUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
       Optional<User> opt =userDao.findByEmail(email);
       if(opt.isPresent()){
           User user = opt.get();
           return user;
       }else{
           throw new UserException("User not found with email"+email);
       }
    }

    @Override
    public List<User> getAllUsers() throws UserException {
           List<User> users =userDao.findAll();
           if(users.isEmpty()) throw new UserException("No any user present");
           return users;
    }
}
