package com.lotteon.dto.requestDto;

import com.lotteon.entity.product.CartItemOption;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCartDto {

    private long id;
    private long custId;
    private long prodId;
    private int quantity;
    private List<Long> options;





}

