package com.lotteon.service.product;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCartDto;
import com.lotteon.dto.responseDto.CartSessionDto;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.product.*;
import com.lotteon.repository.member.MemberRepository;
import com.lotteon.repository.product.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


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
    public ResponseEntity<?> insertCart(PostCartDto postCartDto, HttpSession session) {

        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        // 인증 정보가 없는 경우
        if (auth == null) {
            // responseDto를 생성해서 세션에 저장
            CartSessionDto cartSessionDto = new CartSessionDto(postCartDto.getProdId(), postCartDto.getQuantity(), postCartDto.getOptions());
            session.setAttribute("cartSession", cartSessionDto);  // 세션에 저장

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated. Cart saved to session.");
        }

        Customer customer = auth.getUser().getCustomer();
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found");
        }

        long custId = customer.getId();

        //장바구니 조회 없으면 생성
        Optional<Cart> optCart = cartRepository.findByCustId(custId);

        Cart cart = null;
        if(!optCart.isPresent()) {
            cart = Cart.builder().custId(custId).build();
            cartRepository.save(cart);
        }else{
            cart = optCart.get();
        }

        return ResponseEntity.ok(cart);

    }

    public ResponseEntity<?> insertCartItem(PostCartDto postCartDto, Cart cart) {

        long prodId = postCartDto.getProdId();
        Product product = productRepository.findById(prodId).orElse(null);
        List<ProductOption> prodOptions = product.getOptions();//프로덕트 옵션 뽑기
        List<CartItem> existingCartItems = cartItemRepository.findAllByCartAndProduct(cart, product);

        //프로덕트 옵션과 dto 옵션이 일치하는지 확인
        log.info("prod Options : "+prodOptions.toString());

        List<Long> dtoOptions = postCartDto.getOptions();
        log.info("dtoOptions : "+dtoOptions.toString());

        List<Long> matchOption = postCartDto.getOptions().stream()
                .filter(dto -> prodOptions.stream().anyMatch(prod -> Objects.equals(dto, prod.getId()))).collect(Collectors.toList());

        log.info("matchOptions : "+matchOption.toString());

        CartItem existingCartItem = null;
        for (CartItem cartItem : existingCartItems) {
            // 3. 선택된 옵션이 동일한지 확인
            List<CartItemOption> existingOptions = cartItem.getSelectedOptions();
            List<Long> existingOptionIds = existingOptions.stream()
                    .map(CartItemOption::getProdOptionId)
                    .collect(Collectors.toList());

            // 선택된 옵션 리스트가 동일한지 비교
            if (new HashSet<>(existingOptionIds).containsAll(matchOption) && new HashSet<>(matchOption).containsAll(existingOptionIds)) {
                existingCartItem = cartItem;
                break;
            }
        }
        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + postCartDto.getQuantity();
            double newPrice = newQuantity * product.getProdPrice();
            existingCartItem.setTotalPrice(newPrice);
            existingCartItem.setQuantity(newQuantity);
            cartItemRepository.save(existingCartItem);
        } else {

            CartItem newCartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(postCartDto.getQuantity())
                    .totalPrice(product.getProdPrice() * postCartDto.getQuantity())
                    .build();

            List<CartItemOption> newOptions = new ArrayList<>();
            for (Long optionId : matchOption) {
                CartItemOption newOption = CartItemOption.builder()
                        .cartItem(newCartItem)
                        .prodOptionId(optionId)
                        .build();
                newOptions.add(newOption);
            }
            newCartItem.getSelectedOptions().addAll(newOptions);
            cartItemRepository.save(newCartItem);

        }

        return ResponseEntity.ok().body("insert");
    }
}

