package com.lotteon.controller.controller;

import com.lotteon.dto.ArticleDto;
import com.lotteon.entity.category.CategoryArticle;
import com.lotteon.service.article.FaqService;
import com.lotteon.service.article.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
@Log4j2
public class CsController {

    private final FaqService faqService;
    private final QnaService qnaService;

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
        return "pages/cs/faq/list";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        return "pages/cs/faq/view";
    }

    @GetMapping("/qnas")
    public String qnas(Model model) {
        return "pages/cs/qna/list";
    }

    @GetMapping("/qna")
    public String qna(Model model) {
        return "pages/cs/qna/view";
    }

    @GetMapping("/qna/write")
    public String qnaWrite(Model model) {
        return "pages/cs/qna/write";
    }


    // 문의하기 글 작성 처리
    @PostMapping("/qna/write")
    public String submitQna(@ModelAttribute ArticleDto articleDto, HttpServletRequest req) {
        Long qnaId = qnaService.insertQna(articleDto, req);
        log.info("문의하기 글 작성 완료, ID: " + qnaId);
        return "redirect:/cs/qnas";  // 작성 후 목록 페이지로 이동
    }

    // 문의하기 목록 페이지
    @GetMapping("/qnas")
    public String qnaList() {
        return "pages/cs/qna/list"; // 목록 페이지 이동 (리스트 데이터를 추가하면 됨)
    }
}
