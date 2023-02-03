package com.bookers.service;

import com.bookers.exception.UserException;
import com.bookers.model.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User getUserByEmail(String email)throws UserException;

    public List<User> getAllUsers()throws UserException;
}
