package com.lotteon.controller.controller;


import com.lotteon.dto.requestDto.PostCustSignupDTO;
import com.lotteon.dto.responseDto.GetTermsResponseDto;
import com.lotteon.entity.term.Terms;
import com.lotteon.service.member.CustomerService;
import com.lotteon.service.member.MemberService;
import com.lotteon.service.member.SellerService;
import com.lotteon.service.term.TermsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerService customerService;
    private final MemberService memberService;
    private final SellerService sellerService;
    private final TermsService termsService;

    @GetMapping("/login/view")
    public String login() {
        return "pages/auth/login";
    }

    @GetMapping("/join")
    public String join() {
        return "pages/auth/join";
    }
    
    // 이용약관 Terms
    @GetMapping("/signup")
    public String signUp(@RequestParam String termsType, Model model) {
        List<GetTermsResponseDto> getterms = termsService.selectTerms(termsType);
        log.info(getterms);
        model.addAttribute("terms", getterms);
        return "pages/auth/signup";
    }

    // 2. 회원가입 (일반회원 정보입력) | optional : 선택약관 동의 여부
    @GetMapping("/customer/{optional}")
    public String customer(@PathVariable("optional") String optional, Model model) {
        return "pages/auth/customer";
    }

    // 2. 회원가입 (일반회원 정보입력)
    @PostMapping("/customer")
    public String customer(PostCustSignupDTO postCustSignupDTO) {
        customerService.insertCustomer(postCustSignupDTO);
        log.info("11111111111"+postCustSignupDTO);
        return "pages/auth/customer";
    }

    // 회원가입 (판매회원 정보입력)
    @GetMapping("/seller")
    public String seller(){
        return "pages/auth/seller";
    }

}
