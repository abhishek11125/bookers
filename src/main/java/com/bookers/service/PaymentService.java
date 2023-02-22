package com.bookers.service;

import com.bookers.exception.PaymentException;
import com.bookers.model.Payment;

public interface PaymentService {
    public Integer proceedToPayment(Payment payment,Integer customerId)throws PaymentException;
}
