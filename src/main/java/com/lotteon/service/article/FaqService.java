package com.lotteon.service.article;

import com.lotteon.dto.ArticleDto;
import com.lotteon.entity.article.Faq;
import com.lotteon.entity.category.CategoryArticle;
import com.lotteon.repository.article.FaqRepository;
import com.lotteon.repository.category.CategoryArticleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;
    private final CategoryArticleRepository categoryArticleRepository;
    private final ModelMapper modelMapper;

    public void writeFaq(String cate1, String cate2, String title, String content) {
        // 1. CategoryArticleRepository에서 cate1과 cate2에 해당하는 카테고리를 찾고 값이 없으면 바로 예외를 던짐
        CategoryArticle category1 = categoryArticleRepository.findByCate1(cate1)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 1을 찾을 수 없습니다: " + cate1));
        CategoryArticle category2 = categoryArticleRepository.findByCate2(cate2)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 2를 찾을 수 없습니다: " + cate2));

        // 2. Faq 엔티티를 생성하여 저장
        Faq faq = Faq.builder()
                .cate1(category1)
                .cate2(category2)
                .faqTitle(title)
                .faqContent(content)
                .build();

        // 3. FAQ 저장
        faqRepository.save(faq);
    }

    // FAQ 목록 조회 (카테고리별)
    public Page<ArticleDto> getFaqs(CategoryArticle cate1, CategoryArticle cate2, int limit, Pageable pageable) {
        // Page<Faq>를 사용하여 페이징 처리된 결과를 받아옴
        Page<Faq> faqPage = faqRepository.findByCate1AndCate2(cate1, cate2, pageable);
        // Page<ArticleDto>로 변환하여 반환
        return faqPage.map(faq -> modelMapper.map(faq, ArticleDto.class));
    }

    // FAQ 상세 조회 (ID로 조회)
    public ArticleDto getDetailById(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("FAQ not found"));
        return modelMapper.map(faq, ArticleDto.class); // FAQ -> DTO 변환
    }

    // 더보기 기능 (처음부터 10개 가져오는걸로 수정하기)
    public List<ArticleDto> getFaqsCount10(CategoryArticle cate1, CategoryArticle cate2) {
        Limit limit = Limit.of(10);

        // Page<Faq>를 사용하여 더보기 페이징 처리된 결과를 받아옴
        List<Faq> byCate1AndCate2 = faqRepository.findByCate1AndCate2(cate1, cate2, limit);
        // Page<ArticleDto>로 변환하여 반환
        return byCate1AndCate2.stream().map(faq -> modelMapper.map(faq, ArticleDto.class)).toList();

    }







}
