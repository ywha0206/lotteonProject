package com.lotteon.dto.responseDto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class GetCustomerCouponDto {
    private Long id;
    private Long couponId;
    private String couponType;
    private String couponName;
    private String memUid;
    private int custCouponState;
    private String custCouponUdate;
}
