package com.lara.paymentservice.service;

import com.lara.paymentservice.web.dto.request.PaymentRequest;
import com.lara.paymentservice.web.dto.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
    Long doPayment(PaymentRequest paymentRequest);
}
