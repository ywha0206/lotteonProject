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

    @GetMapping("/orders")
    public String orders(Model model) {
        return "pages/admin/order/list";
    }

    @GetMapping("/deliverys")
    public String deliverys(Model model) {
        return "pages/admin/order/delivery";
    }
}
