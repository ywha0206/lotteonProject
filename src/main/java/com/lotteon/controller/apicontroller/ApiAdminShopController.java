package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.PostAdminSellerDto;
import com.lotteon.service.member.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/shop")
@RequiredArgsConstructor
public class ApiAdminShopController {

    private final SellerService sellerService;

    @PostMapping("/seller")
    public ResponseEntity<?> postSeller(
            @RequestBody PostAdminSellerDto postAdminSellerDto
            ){

        sellerService.postSeller(postAdminSellerDto);


        return ResponseEntity.ok("SU");
    }
}
