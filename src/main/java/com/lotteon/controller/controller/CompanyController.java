package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    @GetMapping("/index")
    public String index() {

        return "pages/company/index";
    }

    @GetMapping("/culture")
    public String culture() {
        return "pages/company/culture";
    }

    @GetMapping("/recruit")
    public String recruit() {
        return "pages/company/recruit";
    }

    @GetMapping("/story")
    public String story() {
        return "pages/company/story";
    }



}

