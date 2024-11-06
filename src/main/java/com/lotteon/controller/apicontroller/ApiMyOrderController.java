package com.lotteon.controller.apicontroller;

import com.lotteon.dto.responseDto.GetSellerInfoDto;
import com.lotteon.dto.responseDto.cartOrder.ResponseOrderDto;
import com.lotteon.service.member.SellerService;
import com.lotteon.service.product.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class ApiMyOrderController {

    private final SellerService sellerService;
    private final OrderItemService orderItemService;

    @GetMapping("/order/seller-info")
    public ResponseEntity<?> sellerInfo(
            @RequestParam String company
    ) {
        GetSellerInfoDto seller = sellerService.findBySellerCompany(company);
        return ResponseEntity.ok(seller);
    }

    @GetMapping("/order/orderInfo")
    public ResponseEntity<?> orderInfo(
            @RequestParam Long orderId
    ) {
        log.info("orderId: " + orderId);
        ResponseOrderDto dtos  = orderItemService.selectMyOrderInfo(orderId);

        return ResponseEntity.ok(dtos);
    }
}
