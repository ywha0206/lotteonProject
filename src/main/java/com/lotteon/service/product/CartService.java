package com.lotteon.service.product;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCartDto;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.product.*;
import com.lotteon.repository.member.CustomerRepository;
import com.lotteon.repository.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ModelMapper modelMapper;

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemOptionRepository cartItemOptionRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    public String insertCart(PostCartDto postCartDto) {

//        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
//                                                                .getAuthentication()
//                                                                .getPrincipal();
//
//        //유저 조회, 없으면 던져
//        long memberId = Long.parseLong(auth.getUsername());

        long memberId = 1;
        Optional<Customer> optCustomer = customerRepository.findByMemberId(memberId);
            //.orElseThrow(() -> new RuntimeException("Customer not found"));
        Customer customer = optCustomer.get();
        long custId = customer.getId();


        //장바구니 조회 없으면 생성
        Optional<Cart> optCart = cartRepository.findByCustId(custId);

        if(!optCart.isPresent()) {
            Cart cart = Cart.builder().custId(custId).build();
            cartRepository.save(cart);
        }
        Cart cart = optCart.get();
        log.info("장바구니 조회한거"+cart.toString());

        long prodId = postCartDto.getProdId();

        //상품 조회하고 상품옵션 조회해서
        Optional<Product> optProduct = productRepository.findById(prodId);//엥?여기서 익셉션으로 넘어감

        Product product = optProduct.get();
        double total = product.getProdPrice() * postCartDto.getQuantity();

        Optional<ProductOption> optProdOption = productOptionRepository.findByProduct(product);

        CartItem cartItem = CartItem.builder()
                                    .cart(optCart.get())
                                    .product(product)
                                    .quantity(postCartDto.getQuantity())
                                    .productOption(optProdOption.get())
                                    .totalPrice(total)
                                    .build();

        cartItemRepository.save(cartItem);

//============================================================================================================
//        //장바구니 아이템 있으면 수정 없으면 추가
//        CartItem cartItem ;
//        Product prod = productRepository.findById(postCartDto.getProdId()).orElse(null);
//        List<CartItem> optCartItem = cartItemRepository.findAllByCartAndProduct(cart, prod);
//            System.out.println(optCartItem);
//        if (optCartItem.isPresent()) {
//            cartItem = optCartItem.get();
//            int dtoQuan = postCartDto.getQuantity();
//            int entityQuan = cartItem.getQuantity();
//            int totalQuan = dtoQuan + entityQuan;
//
//            cartItem.setQuantity(totalQuan);
//        }else{
//            Product product = productRepository.findById(postCartDto.getProdId())
//                    .orElseThrow(() -> new RuntimeException("Product not found"));
//
//            cartItem = CartItem.builder()
//                    .cart(cart)
//                    .product(product)
//                    .quantity(postCartDto.getQuantity())
//                    .totalPrice(product.getProdPrice() * postCartDto.getQuantity())
//                    .build();
//            CartItem cartItem1 = cartItemRepository.save(cartItem);
//
//            //카트 아이템 옵션
//            postCartDto.getOptions().forEach(option -> {
//                CartItemOption cartItemOption = CartItemOption.builder()
//                        .prodOptionId(option)
//                        .cartItem(cartItem1)
//                        .build();
//                cartItemOptionRepository.save(cartItemOption);
//            } );
//        }



        return null;

    }
}

