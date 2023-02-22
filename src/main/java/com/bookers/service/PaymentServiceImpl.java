package com.bookers.service;

import com.bookers.exception.PaymentException;
import com.bookers.model.Customer;
import com.bookers.model.Order;
import com.bookers.model.Payment;
import com.bookers.repository.PaymentDao;
import com.bookers.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private CustomerDao customerDao;


    @Override
    public Integer proceedToPayment(Payment payment) throws PaymentException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer customer = customerDao.findByEmail(authentication.getName());

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
