package com.lotteon.entity.point;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "coupon_name")
    private String couponName;
    @Column(name = "coupon_discount_option")
    private String couponDiscountOption;

    @Column(name = "coupon_discount")
    private int couponDiscount;

    @Column(name = "coupon_type")
    private String couponType;

    @Column(name = "coupon_expiration")
    private String couponExpiration;

    @Column(name = "coupon_min_price")
    private int couponMinPrice;

    @Column(name = "coupon_caution", columnDefinition = "TEXT")
    private String couponCaution;

    @Column(name = "coupon_issuer")
    private Long memId;
}
