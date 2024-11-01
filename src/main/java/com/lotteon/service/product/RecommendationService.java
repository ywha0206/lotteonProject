package com.lotteon.service.product;

import com.lotteon.entity.product.Product;
import com.lotteon.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;

    public List<Product> findRelatedProducts(Long prodId) {
        // 1단계: prodId를 본 고객 찾기
        Aggregation getCustIdsAggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("prodId").is(prodId)),
                Aggregation.group("custId") // 고객 ID로 그룹화
        );

        // 해당 prodId를 본 고객 ID 리스트를 가져옵니다.
        List<Long> custIds = mongoTemplate.aggregate(getCustIdsAggregation, "user_logs", Document.class)
                .getMappedResults().stream()
                .map(doc -> doc.getLong("_id")) // 고객 ID를 추출
                .collect(Collectors.toList());

        // 2단계: 해당 고객들이 상호작용한 다른 상품 ID 집계
        Aggregation relatedProductsAggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("custId").in(custIds)), // 특정 고객들만 필터링
                Aggregation.group("prodId").count().as("interactionCount"), // 상품별 카운트
                Aggregation.sort(Sort.by("interactionCount").descending()), // 상호작용 횟수로 정렬
                Aggregation.limit(5) // 상위 5개 상품
        );

        List<Long> relatedProductIds = mongoTemplate.aggregate(relatedProductsAggregation, "user_logs", Document.class)
                .getMappedResults().stream()
                .map(doc -> doc.getLong("_id")) // 연관 상품 ID 추출
                .filter(id -> !id.equals(prodId))
                .collect(Collectors.toList());

        System.out.println(relatedProductIds);
        System.out.println(productRepository.findAllByIdIn(relatedProductIds));
        return productRepository.findAllByIdIn(relatedProductIds);
    }

}