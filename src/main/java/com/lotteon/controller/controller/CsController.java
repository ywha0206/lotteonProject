package com.lotteon.controller.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
@Log4j2
public class CsController {
    @GetMapping("/index")
    public String join(Model model) {
        return "pages/cs/index";
    }

    @GetMapping("/notices")
    public String notices(Model model) {
        return "pages/cs/notice/list";
    }

    @GetMapping("/notice")
    public String notice(Model model) {
        return "pages/cs/notice/view";
    }

    @GetMapping("/faqs")
    public String faqs(Model model) {
        return "pages/cs/faqs/list";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        return "pages/cs/faqs/view";
    }

    @GetMapping("/qnas")
    public String qnas(Model model) {
        return "pages/cs/qnas/list";
    }

    @GetMapping("/qna")
    public String qna(Model model) {
        return "pages/cs/qna/view";
    }

    @GetMapping("/qna/write")
    public String qnaWrite(Model model) {
        return "pages/cs/qna/write";
    }

}
