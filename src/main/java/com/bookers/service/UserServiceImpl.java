package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.model.UserCurrentSession;
import com.bookers.repository.UserDao;
import com.bookers.exception.UserException;
import com.bookers.model.User;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSessionDao userSessionDao;
    @Override
    public User registerUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
       User user =userDao.findByEmail(email);
       if(user==null) throw new UserException("User not found with email"+email);
       else return user;
    }

    @Override
    public List<User> getAllUsers(String key) throws UserException, AccessDenied {
        UserCurrentSession userCurrentSession =  userSessionDao.findByUid(key);

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        if (opt.isEmpty()) throw new UserException("Please login");
        User user = opt.get();

        if(user.getRole().equalsIgnoreCase("Admin")){
               List<User> users =  userDao.findAll();
               return users;
        }else throw new AccessDenied("Not Authorized");

    }
}
