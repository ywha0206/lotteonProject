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
    
    // 이용약관
    @GetMapping("/signup/{type}")
    public String signUp(@PathVariable("type") String termsType, Model model) {
        GetTermsResponseDto terms = termsService.selectTerms(termsType);
        log.info(terms);
        model.addAttribute("terms", terms);
        return "pages/auth/signup";
    }

    // 2. 회원가입 (일반회원 정보입력) | optional : 선택약관 동의 여부
    @GetMapping("/customer/{optional}")
    public String customer(){
        return "pages/auth/customer";
    }

    // 2. 회원가입 (일반회원 정보입력)
    @PostMapping("/customer")
    public String customer(
             PostCustSignupDTO postCustSignupDTO) {
//        log.info("Register Controller - UserDTO :"+PostCustSignupDTO.toString());
//        System.out.println(postCustSignupDTO 4
//        String regip= req.getRemoteAddr();
//        PostCustSignupDTO.setRegip(regip);
//        customerService.insertCustomer(PostCustSignupDTO);
//        return "redirect:/user/login?success=200";


//        return "redirect:pages/auth/login/customer";
        return "pages/auth/customer";
    }


    // 회원가입 (판매회원 정보입력)
    @GetMapping("/seller")
    public String seller(){
        return "pages/auth/seller";
    }




}
