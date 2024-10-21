package com.lotteon.service.category;

import com.lotteon.entity.category.CategoryArticle;
import com.lotteon.repository.category.CategoryArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryArticleService {
    private final CategoryArticleRepository categoryArticleRepository;

    // 1차 카테고리(부모 카테고리) 가져오기
    public List<CategoryArticle> getParentCategories() {
        return categoryArticleRepository.findByParentIsNull();
    }

    // 2차 카테고리(자식 카테고리) 가져오기
    public List<CategoryArticle> getChildCategories(Long parentCategoryId) {
        CategoryArticle parentCategory = categoryArticleRepository.findById(parentCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 부모 카테고리를 찾을 수 없습니다: " + parentCategoryId));
        return categoryArticleRepository.findByParent(parentCategory);
    }

    public CategoryArticle getCategoryById(Long cate1Id) {
        return null;
    }
}
