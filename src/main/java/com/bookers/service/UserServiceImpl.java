package com.bookers.service;

import com.bookers.exception.*;
import com.bookers.model.*;
import com.bookers.repository.BookDao;
import com.bookers.repository.OrderDao;
import com.bookers.repository.UserDao;
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
    @Autowired
    private OrderDao orderDao;
    @Override
    public User registerUser(User user) {
        if(user.getRole().equalsIgnoreCase("Buyer")) {
            Cart cart = new Cart();
            user.setCart(cart);
            cart.setUser(user);
        }
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

    @Override
    public List<Order> getOrderHistory(String key) throws LoginException,BookException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

       Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

       User user = opt.get();
       List<Order> orders = user.getOrders();
       if(orders.isEmpty()) throw new BookException("No any order placed yet");
       return orders;
    }

    @Override
    public Order cancelOrder(Integer orderId, String key) throws LoginException,OrderException {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null) throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        Optional<Order> opt2 = orderDao.findById(orderId);

        if (opt2.isEmpty())throw new OrderException("Order not found with order id "+orderId);

        Order order = opt2.get();
        order.setOrderStatus("Cancelled");

        List<Order> userOrders = user.getOrders();
        for (Order i:userOrders){
            if(i.getOrderId()==orderId){
                i.setOrderStatus("Cancelled");
                break;
            }
        }

        orderDao.delete(order);

        return order;
    }

}
