package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.PostCouponDto;
import com.lotteon.dto.responseDto.GetCouponDto;
import com.lotteon.service.point.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/coupon")
    public ResponseEntity<?> getCoupon (
            @RequestParam Long id
    ){
        GetCouponDto coupon = couponService.findCoupon(id);
        Map<String,Object> map = new HashMap<>();
        map.put("coupon",coupon);
        return ResponseEntity.ok(map);
    }

    @PatchMapping("/coupon")
    public ResponseEntity<?> updateCoupon (
            @RequestBody PostCouponDto postCouponDto
    ){
        couponService.updateCouponState(postCouponDto.getId());

        return ResponseEntity.ok("SU");
    }

}
