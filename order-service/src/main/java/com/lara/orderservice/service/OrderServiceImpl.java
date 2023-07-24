package com.lara.orderservice.service;

import com.lara.orderservice.domain.Order;
import com.lara.orderservice.exception.OrderServiceCustomException;
import com.lara.orderservice.remote.dto.request.PaymentRequest;
import com.lara.orderservice.remote.service.PaymentRemoteService;
import com.lara.orderservice.remote.service.ProductRemoteService;
import com.lara.orderservice.repository.OrderRepository;
import com.lara.orderservice.web.dto.request.OrderRequest;
import com.lara.orderservice.web.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.time.Instant.now;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRemoteService productRemoteService;
    private final PaymentRemoteService paymentRemoteService;


    @Override
    public OrderResponse getOrderDetails(Long orderId) {
        log.info("Getting order details for Order Id: {}", orderId);

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderServiceCustomException("Order not found for Id: " + orderId, "NOT_FOUND", 404));

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .build();
    }

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        log.info("== Placing a new order ==");

        productRemoteService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Creating order with status CREATED");
        Order order = Order.builder()
                .amount(orderRequest.getAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);

        log.info("Calling Payment Service for order {}", order.getId());
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .mode(orderRequest.getPaymentMode())
                .amount(orderRequest.getAmount())
                .build();

        try {
            paymentRemoteService.doPayment(paymentRequest);
            order.setOrderStatus("PLACED");
            orderRepository.save(order);
            log.info("Order placed successfully, Id {}", order.getId());
            return order.getId();
        } catch (Exception e) {
            log.error("Error occurred in payment {}", e.getMessage());
            order.setOrderStatus("PAYMENT_FAILED");
            orderRepository.save(order);
            throw new OrderServiceCustomException("Error occurred in payment", "PAYMENT_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
