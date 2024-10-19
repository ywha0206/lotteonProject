package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/coupon")
@RequiredArgsConstructor
@Log4j2
public class AdminCouponController {

    private String getSideValue() {
        return "coupon";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/coupons")
    public String coupons(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/coupon/list";
    }

    @GetMapping("/issuearances")
    public String issuances(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/coupon/issuearance";
    }
}
