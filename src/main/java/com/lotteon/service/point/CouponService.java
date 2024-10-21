package com.lotteon.service.point;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCouponDto;

import com.lotteon.dto.responseDto.GetCouponDto;
import com.lotteon.entity.member.Member;
import com.lotteon.entity.member.Seller;
import com.lotteon.entity.point.Coupon;
import com.lotteon.repository.member.MemberRepository;
import com.lotteon.repository.member.SellerRepository;
import com.lotteon.repository.point.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final SellerRepository sellerRepository;

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
            Member member = auth.getUser();
            Page<Coupon> coupons = couponRepository.findAllByMemberOrderByIdDesc(member,pageable);
            dtos = coupons.map(Coupon::toGetCouponDto);
        }
        return dtos;
    }

    public GetCouponDto findCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(()->new NoSuchElementException("Coupon with id " + id + " not found."));
        GetCouponDto dto = coupon.toGetCouponDto();
        return dto;
    }

    public void updateCouponState(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(()->new NoSuchElementException("Coupon with id " + id + " not found."));
        coupon.updateCouponState();
    }

    public Page<GetCouponDto> findAllCouponsBySearch(int page, String searchType, String keyword) {
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Pageable pageable = PageRequest.of(page, 5);
        Page<GetCouponDto> dtos;
        if(auth.getUser().getMemRole().equals("admin")) {
            if (searchType.equals("id")) {
                Page<Coupon> coupons = couponRepository.findAllByIdOrderByIdDesc(Long.parseLong(keyword), pageable);
                dtos = coupons.map(Coupon::toGetCouponDto);
            } else if (searchType.equals("couponName")) {
                Page<Coupon> coupons = couponRepository.findAllByCouponNameContainingOrderByIdDesc(keyword, pageable);
                dtos = coupons.map(Coupon::toGetCouponDto);
            } else {
                Seller seller = sellerRepository.findBySellCompanyContaining(keyword);
                Member member = seller.getMember();

                Page<Coupon> coupons = couponRepository.findAllByMemberOrderByIdDesc(member, pageable);
                dtos = coupons.map(Coupon::toGetCouponDto);
            }
            return dtos;
        } else {
            Member member = auth.getUser();
//            Page<Coupon> coupons = couponRepository.findAllByMemberOrderByIdDesc(member,pageable);
//            dtos = coupons.map(Coupon::toGetCouponDto);
            if (searchType.equals("id")) {
                Page<Coupon> coupons = couponRepository.findAllByIdAndMemberOrderByIdDesc(Long.parseLong(keyword),member, pageable);
                dtos = coupons.map(Coupon::toGetCouponDto);
            } else if (searchType.equals("couponName")) {
                Page<Coupon> coupons = couponRepository.findAllByCouponNameAndMemberContainingOrderByIdDesc(keyword,member, pageable);
                dtos = coupons.map(Coupon::toGetCouponDto);
            } else {
                Seller seller = sellerRepository.findBySellCompanyContaining(keyword);
                Member member2 = seller.getMember();

                Page<Coupon> coupons = couponRepository.findAllByMemberOrderByIdDesc(member2, pageable);
                dtos = coupons.map(Coupon::toGetCouponDto);
            }
            return dtos;
        }

    }
}
