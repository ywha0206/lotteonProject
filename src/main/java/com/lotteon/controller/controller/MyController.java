package com.lotteon.controller.controller;

import com.lotteon.dto.responseDto.GetCustomerCouponDto;
import com.lotteon.dto.responseDto.GetMyCouponDto;
import com.lotteon.dto.responseDto.GetPointsDto;
import com.lotteon.service.member.CustomerService;
import com.lotteon.service.point.CouponService;
import com.lotteon.service.point.CustomerCouponService;
import com.lotteon.service.point.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
@Log4j2
public class MyController {

    private final CouponService couponService;
    private final CustomerCouponService customerCouponService;
    private final CustomerService customerService;
    private final PointService pointService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        int hasCoupon = customerCouponService.findAllCntByCustomer();
        model.addAttribute("hasCoupon", hasCoupon);
        int hasPoint = customerService.findByCustomer();
        model.addAttribute("hasPoint", hasPoint);
    }

    @GetMapping(value = {"","/","/index"})
    public String index(Model model) {
        return "pages/my/index";
    }
    @GetMapping("/coupons")
    public String coupon(
            Model model,
            @RequestParam(name = "page",defaultValue = "0") int page
    ) {
        Page<GetMyCouponDto> coupons = customerCouponService.findAllByCustomer(page);
        if(coupons==null){
            model.addAttribute("noItem",true);
            return "pages/my/coupon";
        }
        model.addAttribute("noItem",false);
        model.addAttribute("coupons", coupons);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", coupons.getTotalPages());

        return "pages/my/coupon";
    }
    @GetMapping("/info")
    public String info(Model model) {
        return "pages/my/info";
    }
    @GetMapping("/orders")
    public String order(Model model) {
        return "pages/my/order";
    }
    @GetMapping("/points")
    public String point(
            Model model,
            @RequestParam(name = "page",defaultValue = "0") int page
            ) {
        Page<GetPointsDto> points = pointService.findAllByCustomer(page);
        if(points.isEmpty()){
            model.addAttribute("noItem",true);
            return "pages/my/point";
        }
        model.addAttribute("noItem",false);
        model.addAttribute("points", points);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", points.getTotalPages());

        return "pages/my/point";
    }
    @GetMapping("/qnas")
    public String qna(Model model) {
        return "pages/my/qna";
    }
    @GetMapping("/reviews")
    public String review(Model model) {
        return "pages/my/review";
    }
}
