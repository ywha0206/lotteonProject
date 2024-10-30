package com.lotteon.dto.requestDto.cartOrder;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCartDto {

    private long custId;
    private long prodId;
    private int quantity;
    private List<Long> options;
    private double totalPrice;





}

