package com.lotteon.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class CsController {
    @GetMapping("/index")
    public String join() {
        return "pages/cs/index";
    }
}
