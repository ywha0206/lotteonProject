package com.lotteon.service.category;

import com.lotteon.dto.responseDto.TestResponseDto;
import com.lotteon.entity.category.CategoryProduct;
import com.lotteon.entity.category.CategoryProductMapper;
import com.lotteon.entity.product.Product;
import com.lotteon.repository.category.CategoryProdMapperRepository;
import com.lotteon.repository.category.CategoryProductRepository;
import com.lotteon.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;
    private final CategoryProdMapperRepository categoryProdMapperRepository;
    private final ProductRepository productRepository;

//    public List<TestResponseDto                                                                                                                                                                                                                                                                                                                                                                                               > findCate() {
//
//        com.lotteon.entity.category.Category parentCategory = cateRepository.findById((long)13)
//                .orElseThrow(() -> new RuntimeException("Parent category not found"));
//
//        List<TestResponseDto> result = new ArrayList<>();
//        findAllChildCategories(parentCategory, result);
//        return result;
//    }

    public List<?> getProducts(){
        // 카테고리별 상품 뽑는법
        CategoryProduct cate = categoryProductRepository.findById((long)28).get();
        List<CategoryProductMapper> products = categoryProdMapperRepository.findAllByCategory(cate);
        if(products.isEmpty()){
            System.out.println("상품없음");
            return null;
        }
        List<Product> productList = products.stream().map(CategoryProductMapper::getProduct).toList();
        System.out.println(productList);


        return null;
    }

    public void findAllChildCategories(CategoryProduct parent, List<TestResponseDto> result) {
        List<CategoryProduct> children = categoryProductRepository.findByParent(parent);
        for (CategoryProduct child : children) {
            result.add(child.toDto()); // DTO 변환하여 결과 리스트에 추가
            findAllChildCategories(child, result); // 하위 카테고리에 대해 재귀 호출
        }
    }
}
