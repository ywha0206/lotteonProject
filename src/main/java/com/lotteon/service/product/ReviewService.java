package com.lotteon.service.product;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostReviewDto;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.product.Product;
import com.lotteon.entity.product.ReviewDocu;
import com.lotteon.repository.product.OrderRepository;
import com.lotteon.repository.product.ProductRepository;
import com.lotteon.repository.product.ReviewDocuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewDocuRepository reviewDocuRepository;

    public String addReview(PostReviewDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProdId());
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = auth.getUser().getCustomer();
        boolean hasOrdered = orderRepository.existsByCustomerAndOrderItems_Product(customer, product.get());

        if (!hasOrdered) {
            return "상품을 구매한 사용자만 리뷰를 작성할 수 있습니다.";
        }

        boolean alreadyReviewed = reviewDocuRepository.existsByCustIdAndProdId(customer.getId(), dto.getProdId());
        if (alreadyReviewed) {
            return "이미 리뷰를 작성하셨습니다. 리뷰는 수정할 수 없습니다.";
        }

        ReviewDocu review = ReviewDocu.builder()
                .reviewScore(dto.getScore())
                .custId(customer.getId())
                .prodId(dto.getProdId())
                .reviewContent(dto.getReview())
                .reviewRdate(LocalDateTime.now())
                .build();
        reviewDocuRepository.save(review);

        List<ReviewDocu> reviews = reviewDocuRepository.findByProdId(dto.getProdId());
        double averageScore = reviews.stream()
                .mapToInt(ReviewDocu::getReviewScore)
                .average()
                .orElse(0.0);

        product.get().updateRating(averageScore);
        productRepository.save(product.get());

        return "리뷰가 성공적으로 작성되었습니다.";
    }
}
