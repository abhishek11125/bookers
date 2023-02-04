package com.bookers.service;

import com.bookers.exception.LoginException;
import com.bookers.model.User;
import com.bookers.model.UserCurrentSession;
import com.bookers.model.UserLoginDTO;
import com.bookers.repository.UserDao;
import com.bookers.repository.UserSessionDao;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSessionDao userSessionDao;

    @Override
    public String logIntoAccount(UserLoginDTO userLoginDTO) throws LoginException {
        Optional<User> opt = userDao.findByEmail(userLoginDTO.getEmail());

        User existingUser  = opt.get();
        if (existingUser==null) throw new LoginException("Incorrect Username");

        Optional<UserCurrentSession> opt1 = userSessionDao.findById(existingUser.getUserId());

        if(opt1.isPresent()) throw new LoginException("User already logged in");

        if(existingUser.getPassword().equals(userLoginDTO.getPassword())){
            String key = RandomString.make(6);
            UserCurrentSession userCurrentSession = new UserCurrentSession(existingUser.getUserId(),key,LocalDateTime.now());
            userSessionDao.save(userCurrentSession);
            return userCurrentSession.getUserKey();
        }else {
            throw new LoginException("Please enter valid password");
        }
    }

    @Override
    public String logOutFromAccount(String key) throws LoginException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUserKey(key);

        if (userCurrentSession==null) throw new LoginException("User not logged in");

        userSessionDao.delete(userCurrentSession);

        return "Logged out successfully";
    }
}
