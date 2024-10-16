package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/shop")
@RequiredArgsConstructor
@Log4j2
public class AdminShopController {

    @GetMapping("/income")
    public String income(Model model) {
        return "pages/admin/shop/income";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        return "pages/admin/shop/shop";
    }
}
