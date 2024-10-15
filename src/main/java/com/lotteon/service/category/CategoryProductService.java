package com.lotteon.service.category;

import com.lotteon.dto.responseDto.TestResponseDto;
import com.lotteon.entity.category.CategoryProduct;
import com.lotteon.repository.category.CategoryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;

//    public List<TestResponseDto                                                                                                                                                                                                                                                                                                                                                                                               > findCate() {
//
//        com.lotteon.entity.category.Category parentCategory = cateRepository.findById((long)13)
//                .orElseThrow(() -> new RuntimeException("Parent category not found"));
//
//        List<TestResponseDto> result = new ArrayList<>();
//        findAllChildCategories(parentCategory, result);
//        return result;
//    }

    public void findAllChildCategories(CategoryProduct parent, List<TestResponseDto> result) {
        List<CategoryProduct> children = categoryProductRepository.findByParent(parent);
        for (CategoryProduct child : children) {
            result.add(child.toDto()); // DTO 변환하여 결과 리스트에 추가
            findAllChildCategories(child, result); // 하위 카테고리에 대해 재귀 호출
        }
    }
}
