package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cs")
@RequiredArgsConstructor
@Log4j2
public class AdminCsController {

    private String getSideValue() {
        return "cs";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/index";
    }
    @GetMapping("/faqs")
    public String faqs(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/faq/list";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/faq/view";
    }


    @GetMapping("/faq/write")
    public String faqWrite(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/faq/write";
    }

    @GetMapping("/faq/modify")
    public String faqModify(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/faq/modify";
    }

    @GetMapping("/qnas")
    public String qnas(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/qna/list";
    }

    @GetMapping("/qna/modify")
    public String qnaModify(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/qna/modify";
    }

    @GetMapping("/qna")
    public String qnaView(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/qna/view";
    }

    @GetMapping("/qna/write")
    public String qnaWrite(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/cs/qna/write";
    }

}
