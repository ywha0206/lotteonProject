package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {

    @GetMapping(value = {"","/","/index"})
    public String index() {
        return "pages/my/index";
    }
    @GetMapping("/coupon")
    public String coupon() {
        return "pages/my/coupon";
    }
    @GetMapping("/info")
    public String info() {
        return "pages/my/info";
    }
    @GetMapping("/order")
    public String order() {
        return "pages/my/order";
    }
    @GetMapping("/point")
    public String point() {
        return "pages/my/point";
    }
    @GetMapping("/qna")
    public String qna() {
        return "pages/my/qna";
    }
    @GetMapping("/review")
    public String review() {
        return "pages/my/review";
    }
}
