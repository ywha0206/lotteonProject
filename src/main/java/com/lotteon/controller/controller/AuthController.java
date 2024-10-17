package com.lotteon.controller.controller;


import com.lotteon.dto.responseDto.GetTermsResponseDto;
import com.lotteon.entity.term.Terms;
import com.lotteon.service.term.TermsService;

import lombok.extern.log4j.Log4j2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TermsService termsService;

    @GetMapping("/login/view")
    public String login() {
        return "pages/auth/login";
    }

    @GetMapping("/join")
    public String join() {
        return "pages/auth/join";
    }
    
    // 이용약관
    @GetMapping("/signup/{type}")
    public String signUp(@PathVariable("type") String termsType, Model model) {
        GetTermsResponseDto terms = termsService.selectTerms(termsType);
        log.info(terms);
        model.addAttribute("terms", terms);
        return "pages/auth/signup";
    }


}
