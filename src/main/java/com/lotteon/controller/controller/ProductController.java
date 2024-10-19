package com.lotteon.controller.controller;

import com.lotteon.dto.responseDto.GetCategoryDto;
import com.lotteon.service.category.CategoryProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/prod")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final CategoryProductService categoryProductService;

    @GetMapping("/products")
    public String products(Model model) {
        List<GetCategoryDto> category1 = categoryProductService.findCategory();

        model.addAttribute("category1", category1);
        return "pages/product/list";
    }

    @GetMapping("/product")
    public String product(Model model) {
        List<GetCategoryDto> category1 = categoryProductService.findCategory();

        model.addAttribute("category1", category1);
        return "pages/product/view";
    }


    @GetMapping("/products/search")
    public String search(Model model) {
        List<GetCategoryDto> category1 = categoryProductService.findCategory();

        model.addAttribute("category1", category1);
        return "pages/product/search";
    }

}
