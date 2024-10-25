package com.lotteon.controller.controller;

import com.lotteon.dto.responseDto.GetCategoryDto;
import com.lotteon.service.category.CategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryProductService categoryProductService;

    @GetMapping(value = {"/","/index"})
    public String main(Model model, @RequestParam(value = "birth", defaultValue = "false") Boolean birth) {
        List<GetCategoryDto> category1 = categoryProductService.findCategory();

        model.addAttribute("category1", category1);
        model.addAttribute("birth", birth);
        return "index";
    }
}
