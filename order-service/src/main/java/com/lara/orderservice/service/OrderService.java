package com.lara.orderservice.service;

import com.lara.orderservice.web.dto.request.OrderRequest;

public interface OrderService {

//    OrderResponse getOrderDetails(Long orderId);
//
    Long placeOrder(OrderRequest orderRequest);

}
