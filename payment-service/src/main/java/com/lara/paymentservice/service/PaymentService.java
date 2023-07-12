package com.lara.paymentservice.service;

import com.lara.paymentservice.web.dto.request.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
