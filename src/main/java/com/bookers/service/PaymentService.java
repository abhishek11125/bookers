package com.bookers.service;

import com.bookers.exception.AccessDenied;
import com.bookers.exception.LoginException;
import com.bookers.exception.PaymentException;
import com.bookers.model.Payment;

public interface PaymentService {
    public Integer proceedToPayment(Payment payment,String key)throws PaymentException, LoginException, AccessDenied;
}
