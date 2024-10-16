package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/user")
@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminUserController {

    @GetMapping("/user")
    public String user(Model model) {
        return "pages/admin/user/user";
    }

    @GetMapping("/point")
    public String point(Model model) {
        return "pages/admin/user/point";
    }
}
