package com.lara.productservice.web.controller;


import com.lara.productservice.service.ProductService;
import com.lara.productservice.web.dto.request.ProductRequest;
import com.lara.productservice.web.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductId(id));
    }

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.addProduct(productRequest), CREATED);
    }

    @PutMapping("/reduce-quantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId, @RequestParam Long quantity) {
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(OK);
    }

}
