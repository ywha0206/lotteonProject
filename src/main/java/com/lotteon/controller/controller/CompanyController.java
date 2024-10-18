package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
@Log4j2
public class CompanyController {

    @GetMapping("/index")
    public String index(Model model) {

        return "pages/company/index";
    }

    @GetMapping("/culture")
    public String culture(Model model) {
        return "pages/company/culture";
    }

    @GetMapping("/recruit")
    public String recruit(Model model) {
        return "pages/company/recruit";
    }

    @GetMapping("/story")
    public String story(Model model) {
        return "pages/company/story";
    }

    @GetMapping("/media")
    public String media(Model model) {
        return "pages/company/media";
    }










}

