package com.lotteon.controller.apicontroller;

import com.lotteon.dto.responseDto.GetDeliveryDateDto;
import com.lotteon.dto.responseDto.GetReceiveConfirmDto;
import com.lotteon.dto.responseDto.GetSellerInfoDto;
import com.lotteon.service.member.SellerService;
import com.lotteon.service.product.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/order/delivery-date")
    public ResponseEntity<?> deliveryDate(
            @RequestParam Long id
    ){
        List<GetDeliveryDateDto> deliveryDate = orderItemService.findDeliveryDateAllByOrderId(id);

        return ResponseEntity.ok(deliveryDate);
    }

    @GetMapping("/order/receive")
    public ResponseEntity<?> receive(
            @RequestParam Long id
    ){
        List<GetReceiveConfirmDto> confirms = orderItemService.findReceiveAllByOrderId(id);
        return ResponseEntity.ok(confirms);
    }

    @PatchMapping("/order/receive")
    public ResponseEntity<?> patchReceive(
            @RequestParam Long id
    ){
        orderItemService.patchItemState(id,5,1,3);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/order/return")
    public ResponseEntity<?> patchReturn(
            @RequestParam Long id
    ){
        orderItemService.patchItemState(id,2,1,3);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/order/change")
    public ResponseEntity<?> patchChange(
            @RequestParam Long id
    ){
        orderItemService.patchItemState(id,3,1,3);
        return ResponseEntity.ok().build();
    }
}
