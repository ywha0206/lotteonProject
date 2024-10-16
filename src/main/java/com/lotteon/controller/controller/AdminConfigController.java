package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class AdminConfigController {
    @GetMapping("/index")
    public String index() {
        return "pages/admin/index";
    }
    @GetMapping("/banner")
    public String banner() {
        return "pages/admin/config/banner";
    }
    @GetMapping("/basic")
    public String basic() {
        return "pages/admin/config/basic";
    }
    @GetMapping("/terms")
    public String terms() {
        return "pages/admin/config/terms";
    }
    @GetMapping("/version")
    public String version() {
        return "pages/admin/config/version";
    }


}
