package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.Book;
import com.bookers.model.UserCurrentSession;
import com.bookers.repository.BookDao;
import com.bookers.repository.UserDao;
import com.bookers.model.User;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
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
    public List<User> getAllUsers(String key) throws LoginException, AccessDenied {
        UserCurrentSession userCurrentSession =  userSessionDao.findByUid(key);

        if (userCurrentSession==null) throw new LoginException("Please Login");
        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        if(user.getRole().equalsIgnoreCase("Admin")){
               List<User> users =  userDao.findAll();
               return users;
        }else throw new AccessDenied("Not Authorized");

    }

    @Override
    public User updateMobile(String mobile, String key) throws LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

       Optional<User> user = userDao.findById(userCurrentSession.getUserId());

       User user1 = opt.get();

       user1.setMobile(mobile);

       userDao.save(user1);
       return  user1;
    }

    @Override
    public User updatePassword(String email, String newPass, String key) {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");
        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());


        User user = userDao.findByEmail(email);
        user.setPassword(newPass);
        userDao.save(user);

        return user;
    }

    @Override
    public User getProfile(String key) throws LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);
            if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        return user;
    }
}
