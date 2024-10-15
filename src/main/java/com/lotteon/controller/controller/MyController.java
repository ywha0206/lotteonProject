package com.lotteon.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {

    @GetMapping("/index")
    public String join() {
        return "pages/my/index";
    }
}
