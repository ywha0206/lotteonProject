package com.lotteon.dto.requestDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class PostReviewDto {
    private Long orderId;
    private Long prodId;
    private Integer score;
    private String review;
}
