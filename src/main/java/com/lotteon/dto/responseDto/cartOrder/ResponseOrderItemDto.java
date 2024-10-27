package com.lotteon.dto.responseDto.cartOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderItemDto {
    private String prodListImg;
    private String prodName;
    private String prodSummary;
    private int prodPrice;
    private int discount;
    private int quantity;
    private int totalPrice;
}
