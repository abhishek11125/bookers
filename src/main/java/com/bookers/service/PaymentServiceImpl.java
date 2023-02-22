package com.bookers.service;

import com.bookers.exception.PaymentException;
import com.bookers.model.Customer;
import com.bookers.model.Order;
import com.bookers.model.Payment;
import com.bookers.repository.PaymentDao;
import com.bookers.repository.CustomerDao;
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


    @Override
    public Integer proceedToPayment(Payment payment,Integer customerId) throws PaymentException{
        Optional<Customer> opt = customerDao.findById(customerId);
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
