package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cs")
@RequiredArgsConstructor
public class AdminCsController {

    @GetMapping("/index")
    public String index() {
        return "pages/admin/cs/index";
    }
    @GetMapping("/faq")
    public String faq() {
        return "pages/admin/cs/faq";
    }
}
