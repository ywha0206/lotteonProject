package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/shop")
@RequiredArgsConstructor
@Log4j2
public class AdminShopController {
    @ModelAttribute
    public void pageIndex(Model model) {
        model.addAttribute("config",getSideValue());
    }
    private String getSideValue() {
        return "shop";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/income")
    public String income(Model model) {
        model.addAttribute("active","income");
        return "pages/admin/shop/income";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("active","shop");
        return "pages/admin/shop/shop";
    }
}
