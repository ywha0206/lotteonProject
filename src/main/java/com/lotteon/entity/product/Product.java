package com.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sell_id")
    private Long sellId;

    @Column(name = "prod_summary")
    private String prodSummary;

    @Column(name = "prod_price")
    private Double prodPrice;

    @Column(name = "prod_discount")
    private Double prodDiscount;

    @Column(name = "prod_deli")
    private int prodDeliver;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "prod_list_img")
    private String prodListImg;

    @Column(name = "prod_basic_img")
    private String prodBasicImg;

    @Column(name = "prod_detail_img")
    private String prodDetailImg;

    @Column(name = "prod_stock")
    private int prodStock;

    @Column(name = "prod_order_cnt")
    private int prodOrderCnt;

    @Column(name = "prod_views")
    private int prodViews;

    @Column(name = "prod_rating")
    private int prodRating;

    @Column(name = "prod_point")
    private int prodPoint;

    @Column(name = "prod_rdate")
    @CreationTimestamp
    private Timestamp prodRdate;
}
