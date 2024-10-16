package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/shop")
@RequiredArgsConstructor
public class AdminShopController {

    @GetMapping("/income")
    public String income() {
        return "pages/admin/shop/income";
    }

    @GetMapping("/shop")
    public String shop() {
        return "pages/admin/shop/shop";
    }
}
