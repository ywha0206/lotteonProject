package com.lotteon.controller.apicontroller;

import com.lotteon.dto.responseDto.GetSellerInfoDto;
import com.lotteon.service.member.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class ApiMyOrderController {

    private final SellerService sellerService;

    @GetMapping("/order/seller-info")
    public ResponseEntity<?> sellerInfo(
            @RequestParam String company
    ) {
        GetSellerInfoDto seller = sellerService.findBySellerCompany(company);
        return ResponseEntity.ok(seller);
    }
}
