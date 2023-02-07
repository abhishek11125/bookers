package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.LoginException;
import com.bookers.exception.UserException;
import com.bookers.model.User;
import com.bookers.model.UserCurrentSession;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User getUserByEmail(String email)throws UserException;

    public List<User> getAllUsers(String key)throws UserException, AccessDenied;

}
