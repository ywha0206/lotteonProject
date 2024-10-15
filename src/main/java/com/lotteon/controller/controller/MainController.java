package com.lotteon.controller.controller;

import com.lotteon.service.category.CategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryProductService categoryProductService;

    @GetMapping(value = {"/","/index"})
    public String main(Model model) {


        return "index";
    }
}
