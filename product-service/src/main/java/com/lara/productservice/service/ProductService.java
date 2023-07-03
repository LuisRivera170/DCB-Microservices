package com.lara.productservice.service;

import com.lara.productservice.web.dto.request.ProductRequest;
import com.lara.productservice.web.dto.response.ProductResponse;

public interface ProductService {

    ProductResponse getProductId(Long id);
    Long addProduct(ProductRequest productRequest);

}
