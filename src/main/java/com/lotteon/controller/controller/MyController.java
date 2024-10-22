package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
@Log4j2
public class MyController {

    @GetMapping(value = {"","/","/index"})
    public String index(Model model) {
        return "pages/my/index";
    }
    @GetMapping("/coupons")
    public String coupon(Model model) {
        return "pages/my/coupon";
    }
    @GetMapping("/info")
    public String info(Model model) {
        return "pages/my/info";
    }
    @GetMapping("/orders")
    public String order(Model model) {
        return "pages/my/order";
    }
    @GetMapping("/points")
    public String point(Model model) {
        return "pages/my/point";
    }
    @GetMapping("/qnas")
    public String qna(Model model) {
        return "pages/my/qna";
    }
    @GetMapping("/reviews")
    public String review(Model model) {
        return "pages/my/review";
    }
}
