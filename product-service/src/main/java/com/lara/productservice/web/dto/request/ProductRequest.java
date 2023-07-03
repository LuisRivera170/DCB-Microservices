package com.lara.productservice.web.dto.request;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private Long price;
    private Long quantity;

}
