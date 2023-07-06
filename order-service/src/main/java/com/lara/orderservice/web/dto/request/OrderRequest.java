package com.lara.orderservice.web.dto.request;

import com.lara.orderservice.domain.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private Long productId;
    private Long amount;
    private Long quantity;
    private PaymentMode paymentMode;

}
