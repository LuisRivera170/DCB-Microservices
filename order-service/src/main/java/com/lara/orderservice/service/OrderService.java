package com.lara.orderservice.service;

import com.lara.orderservice.web.dto.request.OrderRequest;
import com.lara.orderservice.web.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse getOrderDetails(Long orderId);

    Long placeOrder(OrderRequest orderRequest);

}
