package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.LoginException;
import com.bookers.exception.PaymentException;
import com.bookers.model.Order;
import com.bookers.model.Payment;
import com.bookers.model.User;
import com.bookers.model.UserCurrentSession;
import com.bookers.repository.PaymentDao;
import com.bookers.repository.UserDao;
import com.bookers.repository.UserSessionDao;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSessionDao userSessionDao;

    @Override
    public Integer proceedToPayment(Payment payment, String key) throws PaymentException, LoginException, AccessDenied {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null)throw new LoginException("Please Login");

        Optional<User> opt = userDao.findById(userCurrentSession.getUserId());

        User user = opt.get();

        Order order = user.getOrder();
        order.setOrderStatus("Ordered");

        double totalAmount = order.getTotalAmount();

        payment.setPaymentAmount(totalAmount);
        payment.setTimeStamp(LocalDateTime.now());
        payment.setUser(user);
        payment.setOrder(order);
        user.getBuyerPayments().add(payment);

        Payment payment1 = paymentDao.save(payment);

        return payment1.getPaymentId();
    }
}
