package com.lotteon.service.point;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCouponDto;

import com.lotteon.dto.responseDto.GetCouponDto;
import com.lotteon.entity.member.Member;
import com.lotteon.entity.point.Coupon;
import com.lotteon.repository.point.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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


        Member memId = auth.getUser();

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
                .member(memId)
                .couponState("발급중")
                .couponIssueCount(0)
                .couponUseCount(0)

                .build();

        couponRepository.save(coupon);

    }


    public Page<GetCouponDto> findAllCoupons(int page) {
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Pageable pageable = PageRequest.of(page, 5);
        Page<GetCouponDto> dtos;
        if(auth.getUser().getMemRole().equals("admin")){
            Page<Coupon> coupons = couponRepository.findAllByOrderByIdDesc(pageable);
            dtos = coupons.map(Coupon::toGetCouponDto);
        } else {
            dtos = null;
        }
        return dtos;
    }

}
