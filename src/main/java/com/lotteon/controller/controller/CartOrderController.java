package com.lotteon.controller.controller;

import com.lotteon.dto.requestDto.PostCartDto;
import com.lotteon.service.product.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prod")
@RequiredArgsConstructor
@Log4j2
public class CartOrderController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String join(Model model) {
        return "pages/product/cart";
    }

    @PostMapping("/cart")
    public String cart(PostCartDto postCartDto){
        System.out.println("컨트롤러접속");
        System.out.println(postCartDto);
        String result = cartService.insertCart(postCartDto);
        System.out.println(result);
        return "/";
    }

    @GetMapping("/cart/direct")
    public String cartDirect(Model model) {
        return "redirect:/prod/order";
    }

    @GetMapping("/order")
    public String order(Model model) {
        return "pages/product/order";
    }

    @GetMapping("/order/complete")
    public String orderComplete(Model model) {
        return "pages/product/complete";
    }
}
