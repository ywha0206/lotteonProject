package com.lotteon.controller.apicontroller;

import com.lotteon.entity.point.CustomerCoupon;
import com.lotteon.service.point.CustomerCouponService;
import com.lotteon.service.product.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prod")
@RequiredArgsConstructor
public class ApiProductController {
    private final CustomerCouponService customerCouponService;
    private final CartService cartService;

    @GetMapping("/test/coupon")
    public void toTestCouponIssue(){

        customerCouponService.useCustCoupon();
    }

    @DeleteMapping("/cart")
    public ResponseEntity<Long> CartDelete(@RequestBody Map<String,List<Long>> cartItemIds ){
        Long deletedCartItem = cartService.deleteCartItem(cartItemIds);
        return ResponseEntity.ok(deletedCartItem);
    }
}
