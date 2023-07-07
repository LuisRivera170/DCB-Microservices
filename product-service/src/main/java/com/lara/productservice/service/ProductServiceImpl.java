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
                        new ProductServiceCustomException(String.format("Product with given id %d not found", id), "PRODUCT_NOT_FOUND"));

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
        log.info("Product created with id {}", newProduct.getId());
        return newProduct.getId();
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("Reducing quantity {} for product with id {}", quantity, productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(String.format("Product with given id %d not found", productId), "PRODUCT_NOT_FOUND"));

        if (quantity > product.getQuantity()) {
            throw new ProductServiceCustomException(String.format("Product with given id %d hasn't enough quantity", productId), "NOT_ENOUGH_QUANTITY");
        }

        product.setQuantity(product.getQuantity() - quantity);

        log.info("Product quantity updated successfully, remaining quantity {} of product with id {}", product.getQuantity(), product.getId());
        productRepository.save(product);
    }

}
