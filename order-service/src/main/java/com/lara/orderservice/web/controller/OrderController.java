package com.lara.orderservice.web.controller;

import com.lara.orderservice.service.OrderService;
import com.lara.orderservice.web.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
//
//    @GetMapping("/{orderId}")
//    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long orderId) {
//        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
//        return ResponseEntity.ok(orderResponse);
//    }

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }

}
