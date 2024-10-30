package com.lotteon.dto.responseDto;

import com.lotteon.dto.responseDto.cartOrder.CartProductDto;
import com.lotteon.entity.product.CartItem;
import com.lotteon.entity.product.CartItemOption;
import com.lotteon.entity.product.Product;
import com.lotteon.entity.product.ProductOption;
import lombok.*;

import java.util.List;

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
    private String optionValue;
    private String optionValue2;
    private String optionValue3;
    private CartProductDto cartProductDto;
}
