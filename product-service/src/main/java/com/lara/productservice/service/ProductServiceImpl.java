package com.lara.productservice.service;

import com.lara.productservice.domain.Product;
import com.lara.productservice.exception.ProductServiceCustomException;
import com.lara.productservice.repository.ProductRepository;
import com.lara.productservice.web.dto.request.ProductRequest;
import com.lara.productservice.web.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProductId(Long id) {
        log.info("Getting product of Id {}", id);
        Product product = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProductServiceCustomException(String.format("Product with given Id %d not found", id), "PRODUCT_NOT_FOUND"));

        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("Adding product \"{}\"...", productRequest.getName());
        Product newProduct = Product
                .builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(newProduct);
        log.info("Product created with Id {}", newProduct.getId());
        return newProduct.getId();
    }

}
