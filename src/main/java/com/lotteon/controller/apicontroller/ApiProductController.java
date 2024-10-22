package com.lotteon.controller.apicontroller;

import com.lotteon.entity.point.CustomerCoupon;
import com.lotteon.service.point.CustomerCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prod")
@RequiredArgsConstructor
public class ApiProductController {
    private final CustomerCouponService customerCouponService;

    @GetMapping("/test/coupon")
    public void toTestCouponIssue(){

        customerCouponService.useCustCoupon();
    }
}
