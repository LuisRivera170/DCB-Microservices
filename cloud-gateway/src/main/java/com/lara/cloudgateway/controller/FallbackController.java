package com.lara.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    private static final String TRY_AGAIN_LATER = " Please try again later";

    @GetMapping("/orderServiceFallback")
    public String orderServiceFallbackMethod() {
        return "Order Service is taking longer than Expected." +
                TRY_AGAIN_LATER;
    }

    @GetMapping("/paymentServiceFallback")
    public String paymentServiceFallbackMethod() {
        return "Payment Service is taking longer than Expected." +
                TRY_AGAIN_LATER;
    }

    @GetMapping("/productServiceFallback")
    public String productServiceFallbackMethod() {
        return "Product Service is taking longer than Expected." +
                TRY_AGAIN_LATER;
    }

}
