package com.lotteon.dto.responseDto;

import com.lotteon.entity.product.CartItem;
import com.lotteon.entity.product.Product;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCartDto {
    private Long cartItemId;
    private int quantity;
    private double totalPrice;
    private Product product;
}
