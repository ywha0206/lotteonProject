package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
@Log4j2
public class AdminOrderController {

    private String getSideValue() {
        return "order";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/order/list";
    }

    @GetMapping("/deliverys")
    public String deliverys(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/order/delivery";
    }
}
