package com.lotteon.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cs")
public class AdminCsController {

    @GetMapping("/index")
    public String index() {
        return "pages/admin/cs/index";
    }
}
