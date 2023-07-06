package com.lara.orderservice.service;

import com.lara.orderservice.domain.Order;
import com.lara.orderservice.repository.OrderRepository;
import com.lara.orderservice.web.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static java.time.Instant.now;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        log.info("== Placing a new order ==");

        log.info("Order Request: {}", orderRequest);
        Order order = Order.builder()
                .amount(orderRequest.getAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);

        log.info("Order placed successfully, Id {}", order.getId());
        return order.getId();
    }

}
