package com.lotteon.controller.controller;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.ArticleDto;
import com.lotteon.dto.responseDto.NoticeResponseDto;
import com.lotteon.entity.article.Notice;
import com.lotteon.entity.article.Qna;
import com.lotteon.repository.category.CategoryArticleRepository;
import com.lotteon.service.article.FaqService;
import com.lotteon.service.article.NoticeService;
import com.lotteon.service.article.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
 *  이름 : 박경림
 *  날짜 : 2024-10-30
 *  작업내용 : index에서 글 목록, 문의하기 글 목록, 글 보기
 *
 *
 * 수정이력
      - 2024/10/31 박경림 - CS qna 카테고리별 QNA 목록 조회 컨트롤러 추가

 * */
@Controller
@RequestMapping("/cs")
@RequiredArgsConstructor
@Log4j2
public class CsController {

    private final FaqService faqService;
    private final QnaService qnaService;
    private final CategoryArticleRepository categoryArticleRepository;
    private final NoticeService noticeService;

    @GetMapping("/index")
    public String join(Model model) {
        // 최신 공지사항 10개를 가져와서 모델에 추가
        List<Notice> noticeList = noticeService.getTop10Notices();
        model.addAttribute("notices", noticeList);

        // 최신 문의하기 리스트 5개
        List<Qna> qnaList = qnaService.getTop5Qnas();
        model.addAttribute("qnas", qnaList);
        return "pages/cs/index";
    }
    /* 공지사항 */
    @GetMapping("/notices")
    public String notices(@RequestParam(required = false) String category, Model model, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "noticeRdate") // 공지사항 등록일 기준으로 정렬
        );

        Page<NoticeResponseDto> noticeList;
        if (category != null && !category.isEmpty()) {
            noticeList = noticeService.getNoticesByCate1(category, sortedPageable); // cate1으로 조회
        } else {
            noticeList = noticeService.getNotices(null, sortedPageable); // 전체 조회
        }

        model.addAttribute("notices", noticeList);
        model.addAttribute("selectedCate1", category); // 선택된 cate1 전달
        return "pages/cs/notice/list"; // list.html 파일로 이동
    }


    // 개별 공지사항 상세 페이지 매핑 추가
    @GetMapping("/notice")
    public String notice(Model model) {

        return "pages/cs/notice/view";
    }

    @GetMapping("/notice/{id}")
    public String notice(@PathVariable Long id, Model model) {
        NoticeResponseDto notice = noticeService.getNotice(id);  // NoticeResponseDto로 가져오기
        model.addAttribute("notice", notice);  // 모델에 추가
        return "pages/cs/notice/view";  // 뷰 파일로 이동
    }

    @GetMapping("/notice/view/{id}")
    public String getNoticeDetail(@PathVariable Long id, Model model) {
        NoticeResponseDto notice = noticeService.incrementViewsAndGetNotice(id);  // 조회수 증가 후 데이터 조회
        model.addAttribute("notice", notice);  // 조회된 데이터를 모델에 추가하여 뷰로 전달
        return "pages/cs/notice/view";  // 공지사항 상세보기 페이지로 이동
    }


    /* 자주묻는 질문*/
    /* TODO: 자주묻는질문 1,2차 유형별 목록 */
    // 자주 묻는 질문 페이지
    @GetMapping("/faqs")
    public String getFaqs(@RequestParam(required = false) String category,
                          @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                          Model model) {
        // 기본 카테고리 설정
        category = (category == null || category.isEmpty()) ? "user" : category;
        model.addAttribute("selectedCate1", category);

        // 카테고리에 따른 FAQ 목록 페이징 조회
        Page<ArticleDto> faqPage = faqService.getFaqsByCategory(category, pageable);
        List<ArticleDto> faqs = faqPage.getContent();

        // 페이징 정보 및 FAQ 목록을 모델에 추가
        model.addAttribute("faqs", faqs);
        model.addAttribute("page", faqPage);

        return "pages/cs/faq/list";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        return "pages/cs/faq/view";
    }



    /* 문의하기 */
    // 카테고리별 QNA 목록 조회
    @GetMapping("/qnas")
    public String qnasByCategory(
            @RequestParam(required = false) String category,
            Model model,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // category가 null이거나 빈 문자열이면 "회원"을 기본값으로 설정 (전체 글 목록이 없어서)
        if (category == null || category.isEmpty()) {
            category = "user";
        }

        // 카테고리에 해당하는 QNA 목록 조회
        Page<ArticleDto> qnasPage = qnaService.getQnasByCategory(category, pageable);

        model.addAttribute("qnas", qnasPage.getContent());
        model.addAttribute("page", qnasPage);
        model.addAttribute("selectedCate1", category); // 선택된 카테고리 전달

        return "pages/cs/qna/list";
    }


    // QNA 글 보기
    @GetMapping("/qna/view/{id}")
    public String qnaView(@PathVariable Long id, Model model) {
        ArticleDto qna = qnaService.getQnaById(id); // 서비스에서 QNA 가져오기
        model.addAttribute("qna", qna); // 모델에 QNA 데이터 추가

        // answer가 존재할 경우에만 모델에 추가
        if (qna.getAnswer() != null && !qna.getAnswer().isEmpty()) {
            model.addAttribute("hasAnswer", true);
        } else {
            model.addAttribute("hasAnswer", false);
        }

        return "pages/cs/qna/view"; // 상세보기 페이지로 이동
    }


    @GetMapping("/qna/write")
    public String qnaWrite(Model model) {
        return "pages/cs/qna/write";
    }

    // 문의하기 글 작성 처리
    // @AuthenticationPrincipal 인증된 사용자 정보를 가져올 때 사용
    @PostMapping("/qna/write")
    public String submitQna(@ModelAttribute ArticleDto articleDto,  @AuthenticationPrincipal MyUserDetails myUserDetails) {
        if(myUserDetails == null)
        {
            return "redirect:/auth/login/view";
        }
        Long qnaId = qnaService.insertQna(articleDto, myUserDetails.getUser().getId());
        log.info("문의하기 글 작성 완료, ID: " + qnaId);
        return "redirect:/cs/qnas";
    }
}
