package com.lara.paymentservice.service;

import com.lara.paymentservice.domain.TransactionDetails;
import com.lara.paymentservice.enumeration.PaymentMode;
import com.lara.paymentservice.repository.TransactionDetailsRepository;
import com.lara.paymentservice.web.dto.request.PaymentRequest;
import com.lara.paymentservice.web.dto.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {
        log.info("Getting payment details for Order Id: {}", orderId);
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment details not found"));

        return PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .referenceNumber(transactionDetails.getReferenceNumber())
                .build();
    }

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction completed with Id: {}",  transactionDetails.getId());
        return transactionDetails.getId();
    }

}
