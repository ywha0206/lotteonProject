package com.lotteon.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prod")
public class ProductController {
    @GetMapping("/cart")
    public String join() {
        return "pages/product/cart";
    }
}
