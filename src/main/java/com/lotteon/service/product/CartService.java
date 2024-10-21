package com.lotteon.service.product;

import com.lotteon.dto.requestDto.PostCartDto;
import com.lotteon.entity.member.Member;
import com.lotteon.entity.product.*;
import com.lotteon.repository.member.MemberRepository;
import com.lotteon.repository.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemOptionRepository cartItemOptionRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    @Transactional
    public ResponseEntity<?> insertCart(PostCartDto postCartDto) {

        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                                                                .getAuthentication()
                                                                .getPrincipal();

        long custId = auth.getUser().getCustomer().getId();

        //장바구니 조회 없으면 생성
        Optional<Cart> optCart = cartRepository.findByCustId(custId);

        Cart cart = null;
        if(!optCart.isPresent()) {
            cart = Cart.builder().custId(custId).build();
            cartRepository.save(cart);
        }else{
            cart = optCart.get();
        }

        log.info("장바구니 조회한거"+cart.toString());
// ===============================================================================================================
//
//        long prodId = postCartDto.getProdId();
//
//        //상품 조회
//        Optional<Product> optProduct = productRepository.findById(prodId);
//        Product product = optProduct.get();
//
//        //카트 아이템에 상품이 있는지 조회하고
//        List<CartItem> cartItems = cartItemRepository.findAllByCartAndProduct(cart, product);
//        log.info(cartItems.toString());
//
//        // 카트 아이템에 있는 상품의 옵션이 현재 선택한 옵션과 일치하는지 비교
//        List<Long> selectedOptionsIds = cartItem.getSelectedOptions().stream()
//                .map(CartItemOption::getProdOptionId) // CartItemOption에서 prodOptionId 추출
//                .collect(Collectors.toList());
//
//        // PostCartDto에서 넘어온 옵션들과 비교
//        boolean isSameOptions = selectedOptionsIds.containsAll(postCartDto.getOptions())
//                && postCartDto.getOptions().containsAll(selectedOptionsIds);
//
//        if (isSameOptions) {
//            // 옵션이 동일하면 수량과 가격을 업데이트
//            double updatePrice = product.getProdPrice() * postCartDto.getQuantity();
//            int updateQuantity = postCartDto.getQuantity() + cartItem.getQuantity();
//
//            cartItem.setQuantity(updateQuantity);
//            cartItem.setTotalPrice(updatePrice);
//        } else {
//            // 옵션이 다르면 다른 처리 (새로운 CartItem 생성 등)
//        }
//
//
//        //카트 아이템에 있는 상품의 옵션이 현재 선택한 옵션과 일치하면 상품의 옵션과 수량을 업데이트
//
//        postCartDto.getOptions();
//        for (CartItem cartItem : cartItems) {
//
//            if(cartItem.getSelectedOptions().equals(selectedOptions) && product.getId().equals(postCartDto.getProdId())) {
//
//                double updatePrice = product.getProdPrice() * postCartDto.getQuantity();
//                int updateQuantity = postCartDto.getQuantity()+cartItem.getQuantity();
//
//                cartItem.setQuantity(updateQuantity);
//                cartItem.setTotalPrice(updatePrice);
//
//                return ResponseEntity.ok().body("update");
//
//            }else {
//
//                CartItem savedcartItem = CartItem.builder()
//                                    .cart(cart)
//                                    .product(product)
//                                    .quantity(postCartDto.getQuantity())
//                                    .totalPrice(postCartDto.getTotalPrice())
//                                    .build();
//
//                cartItemRepository.save(savedcartItem);
//
//                //카트 아이템 옵션
//                postCartDto.getOptions().forEach(option -> {
//                    CartItemOption cartItemOption = CartItemOption.builder()
//                            .prodOptionId(option)
//                            .cartItem(cartItem)
//                            .build();
//                    cartItemOptionRepository.save(cartItemOption);
//                } );
//
//                return ResponseEntity.ok().body("insert");
//            }
//        }
//        return ResponseEntity.ok().body("?");
    return null;
    }
}

