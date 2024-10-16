package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/user")
@Controller
@RequiredArgsConstructor
public class AdminUserController {

    @GetMapping("/user")
    public String user() {
        return "pages/admin/user/user";
    }

    @GetMapping("/point")
    public String point() {
        return "pages/admin/user/point";
    }
}
