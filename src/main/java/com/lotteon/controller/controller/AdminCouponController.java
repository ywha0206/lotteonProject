package com.lotteon.controller.controller;

import com.lotteon.dto.responseDto.GetCouponDto;
import com.lotteon.service.point.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/coupon")
@RequiredArgsConstructor
@Log4j2
public class AdminCouponController {

    private final CouponService couponService;

    private String getSideValue() {
        return "coupon";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/coupons")
    public String coupons(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        model.addAttribute("config", getSideValue());

        Page<GetCouponDto> coupons = couponService.findAllCoupons(page);
        model.addAttribute("coupons", coupons);
        model.addAttribute("page",page);
        model.addAttribute("totalPages",coupons.getTotalPages());

        return "pages/admin/coupon/list";
    }

    @GetMapping("/issuearances")
    public String issuances(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/coupon/issuearance";
    }
}
