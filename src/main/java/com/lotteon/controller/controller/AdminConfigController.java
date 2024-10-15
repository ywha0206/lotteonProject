package com.lotteon.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/config")
public class AdminConfigController {
    @GetMapping("/index")
    public String join() {
        return "pages/admin/index";
    }

}
