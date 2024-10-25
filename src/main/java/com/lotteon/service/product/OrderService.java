package com.lotteon.service.product;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCartSaveDto;
import com.lotteon.dto.requestDto.cartOrder.OrderDto;
import com.lotteon.dto.responseDto.GetCartDto;
import com.lotteon.dto.responseDto.GetOrderDto;
import com.lotteon.dto.responseDto.cartOrder.CartItemDto;
import com.lotteon.dto.responseDto.cartOrder.CartItemOptionDto;
import com.lotteon.dto.responseDto.cartOrder.ProductDto;
import com.lotteon.entity.product.*;
import com.lotteon.repository.product.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CartItemOptionRepository cartItemOptionRepository;
    private final ModelMapper modelMapper;

    public List<GetOrderDto> selectedOrders(List<PostCartSaveDto> selectedProducts) {

        List<GetOrderDto> orderDtos = new ArrayList<>();

        for(PostCartSaveDto postCartSaveDto : selectedProducts) {

            Long productId = postCartSaveDto.getProductId();
            Optional<Product> product = productRepository.findById(productId);

            ProductDto productDto = ProductDto.builder()
                                            .id(product.get().getId())
                                            .prodName(product.get().getProdName())
                                            .prodDeliver(product.get().getProdDeliver())
                                            .prodPrice(product.get().getProdPrice())
                                            .prodDiscount(product.get().getProdDiscount())
                                            .prodPoint(product.get().getProdPoint())
                                            .prodSummary(product.get().getProdSummary())
                                            .prodListImg(product.get().getProdListImg())
                                            .sellId(product.get().getSellId())
                                            .stock(product.get().getProdStock())
                                            .build();

            Long cartItemId = postCartSaveDto.getCartItemId();
            Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
            CartItemDto cartItemDto = CartItemDto.builder()
                                                .cartId(cartItem.get().getCart().getId())
                                                .id(cartItem.get().getId())
                                                .quantity(cartItem.get().getQuantity())
                                                .build();




            GetOrderDto orderDto = GetOrderDto.builder()
                                            .products(productDto)
                                            .cartItems(cartItemDto)
                                            .build();

            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public Order insertOrder(OrderDto orderDto) {

        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails auth =(MyUserDetails) authentication.getPrincipal();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else if (authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            // 로그인된 사용자 처리
        }
        String zip = orderDto.getReceiverZip();
        String addr1 = orderDto.getReceiverAddr1();
        String addr2 = orderDto.getReceiverAddr2();

        Order saveorder = Order.builder()
                            .customer(auth.getUser().getCustomer())
                            .orderPayment(orderDto.getOrderPayment())
                            .receiverName(orderDto.getReceiverName())
                            .receiverHp(orderDto.getReceiverHp())
                            .receiverAddr(zip+"/"+addr1+"/"+addr2)
                            .build();

        Order order = orderRepository.save(saveorder);

        if(order==null){
            return null;
        }

        return order;
    }
}
