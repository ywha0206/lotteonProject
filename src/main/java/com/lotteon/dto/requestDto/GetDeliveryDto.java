package com.lotteon.dto.requestDto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class GetDeliveryDto {
    private String deliCompany;
    private Long deliveryId;
    private Long orderItemId;
    private String receiverName;
    private String prodName;
    private Integer orderItemSize;
    private Integer orderItemTotalPrice;
    private Integer prodDeli;
    private Integer orderItemState2;
    private LocalDateTime orderRdate;
}