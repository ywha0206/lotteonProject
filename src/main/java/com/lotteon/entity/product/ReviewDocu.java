package com.lotteon.entity.product;

import com.lotteon.entity.member.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Document(collection = "review")
public class ReviewDocu {
    @Id
    private String id;

    @JoinColumn(name = "prod_id")
    private Product product;

    @JoinColumn(name = "cust_id")
    private Customer customer;

    @Column(name = "review_score")
    private int reviewScore;

    @Column(name = "review_content")
    private String reviewContent;

    @Column(name = "review_rdate")
    @CreationTimestamp
    private Timestamp reviewRdate;
}
