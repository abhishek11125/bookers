package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.LoginException;
import com.bookers.exception.PaymentException;
import com.bookers.model.Customer;
import com.bookers.model.Order;
import com.bookers.model.Payment;
import com.bookers.model.UserCurrentSession;
import com.bookers.repository.PaymentDao;
import com.bookers.repository.CustomerDao;
import com.bookers.repository.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private UserSessionDao userSessionDao;

    @Override
    public Integer proceedToPayment(Payment payment, String key) throws PaymentException, LoginException, AccessDenied {
        UserCurrentSession userCurrentSession = userSessionDao.findByUid(key);

        if(userCurrentSession==null)throw new LoginException("Please Login");

        Optional<Customer> opt = customerDao.findById(userCurrentSession.getUserId());

        Customer customer = opt.get();

        Order order = customer.getOrder();
        order.setOrderStatus("Ordered");

        double totalAmount = order.getTotalAmount();

        payment.setPaymentAmount(totalAmount);
        payment.setTimeStamp(LocalDateTime.now());
        payment.setCustomer(customer);
        payment.setOrder(order);
        customer.getBuyerPayments().add(payment);

        Payment payment1 = paymentDao.save(payment);

        return payment1.getPaymentId();
    }
}
