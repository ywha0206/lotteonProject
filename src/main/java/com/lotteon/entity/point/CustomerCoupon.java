package com.lotteon.entity.point;

import com.lotteon.dto.responseDto.GetCustomerCouponDto;
import com.lotteon.entity.member.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Column(name = "cust_coupon_state")
    private int couponState;

    @Column(name = "coupon_cnt")
    private int couponCnt;

    @Column(name = "cust_coupon_udate")
    private LocalDateTime couponUDate;

    public void updateCustCouponCnt() {
        this.couponCnt = couponCnt + 1;
    }

    public void updateCustCouponCntMinus(){
        LocalDateTime today = LocalDateTime.now();
        this.couponCnt = couponCnt - 1;
        this.couponUDate = today;
        this.couponState = 2;
    }

    public void updateCustCouponState(){
        this.couponState = 0;
    }

    public GetCustomerCouponDto toGetCustomerCouponDto(){
        String uDate;
        if(couponUDate==null){
            uDate = "미사용";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            uDate = couponUDate.format(formatter);
        }
        return GetCustomerCouponDto.builder()
                .couponId(coupon.getId())
                .id(id)
                .couponName(coupon.getCouponName())
                .couponType(coupon.getCouponType())
                .custCouponState(couponState)
                .memUid(customer.getMember().getMemUid())
                .custCouponUdate(uDate)
                .build();
    }
}
