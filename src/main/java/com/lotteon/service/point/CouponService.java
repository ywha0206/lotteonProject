package com.lotteon.service.point;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCouponDto;
import com.lotteon.entity.point.Coupon;
import com.lotteon.repository.point.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;

    public void postAdminCoupon(PostCouponDto postCouponDto) {
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Long memId = auth.getUser().getId();
        String couponDiscountString = postCouponDto.getCouponDiscount();
        int slashIndex = couponDiscountString.indexOf('/');
        int couponDiscount = Integer.parseInt(couponDiscountString.substring(0, slashIndex));
        String couponDiscountOption = couponDiscountString.substring(slashIndex + 1);
        Coupon coupon = Coupon.builder()
                .couponMinPrice(postCouponDto.getCouponMinPrice())
                .couponCaution(postCouponDto.getCouponCaution())
                .couponDiscount(couponDiscount)
                .couponDiscountOption(couponDiscountOption)
                .couponName(postCouponDto.getCouponName())
                .couponExpiration(postCouponDto.getCouponExpiration())
                .couponType("주문상품할인")
                .memId(memId)
                .build();

        couponRepository.save(coupon);

    }
}
