package com.lotteon.entity.point;

import com.lotteon.entity.member.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_coupon")
public class CustomerCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @Column(name = "coupon_expiration")
    private Timestamp couponExpiration;

    @Column(name = "coupon_cnt")
    private int couponCnt;
}
