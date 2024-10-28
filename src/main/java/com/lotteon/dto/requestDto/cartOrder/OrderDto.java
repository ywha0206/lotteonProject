package com.lotteon.dto.requestDto.cartOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int orderPayment;
    private String receiverName;
    private String receiverHp;
    private String receiverAddr1;
    private String receiverAddr2;
    private String receiverZip;
}
