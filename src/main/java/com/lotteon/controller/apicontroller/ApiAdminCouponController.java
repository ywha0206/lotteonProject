package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.PostCouponDto;
import com.lotteon.service.point.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/coupon")
@RequiredArgsConstructor
@Log4j2
public class ApiAdminCouponController {

    private final CouponService couponService;

    @PostMapping("/coupon")
    public ResponseEntity<?> postCoupon (
            @RequestBody PostCouponDto postCouponDto
    ){
        couponService.postAdminCoupon(postCouponDto);

        return null;
    }

}
