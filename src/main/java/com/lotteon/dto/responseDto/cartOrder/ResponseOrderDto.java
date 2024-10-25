package com.lotteon.dto.responseDto.cartOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDto {
    private Long orderId;
    private String custName;
    private String custHp;
    private String receiverName;
    private String receiverHp;
    private String receiverAddr;
    private int OrderTotal;
}
