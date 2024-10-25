package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.PostCartSaveDto;
import com.lotteon.dto.requestDto.cartOrder.OrderDto;
import com.lotteon.dto.requestDto.cartOrder.OrderItemDto;
import com.lotteon.dto.requestDto.cartOrder.PostOrderDto;
import com.lotteon.entity.product.Order;
import com.lotteon.service.point.CustomerCouponService;
import com.lotteon.service.product.CartService;
import com.lotteon.service.product.OrderItemService;
import com.lotteon.service.product.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/prod")
@RequiredArgsConstructor
public class ApiProductController {
    private final CustomerCouponService customerCouponService;
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/test/coupon")
    public void toTestCouponIssue(){

        customerCouponService.useCustCoupon();
    }

    @DeleteMapping("/cart")
    public ResponseEntity<Long> CartDelete(@RequestBody List<Long> cartItemIds ){
        Long deletedCartItem = cartService.deleteCartItem(cartItemIds);
        return ResponseEntity.ok(deletedCartItem);
    }

    @PostMapping("/cart/save")
    public ResponseEntity<Boolean> CartToOrder(@RequestBody List<PostCartSaveDto> selectedProducts, HttpSession session){
        log.info("선택한 카트 정보 "+selectedProducts.toString());
        if(selectedProducts.isEmpty()){
            return ResponseEntity.ok(false);
        }else {
            session.setAttribute("selectedProducts", selectedProducts);
            return ResponseEntity.ok(true);
        }
    }


    @PostMapping("/order")
    public ResponseEntity order(@RequestBody PostOrderDto postOrderDto, HttpSession session){
        log.info("컨트롤러에 들어왔나요?"+postOrderDto.toString());
        List<PostCartSaveDto> selectedProducts = (List<PostCartSaveDto>) session.getAttribute("selectedProducts");
        List<Long> cartItemIds = selectedProducts.stream().map(cartItemId -> cartItemId.getCartItemId()).toList();

        log.info("카트 아이템 아이디 세션에 저장된 거 "+cartItemIds.toString());

        OrderDto orderDto = postOrderDto.getOrderDto();
        List<OrderItemDto> orderItemDto = postOrderDto.getOrderItemDto();

        ResponseEntity orderItemResult = orderItemService.insertOrderItem(orderItemDto,orderDto,session);

        //cartService.deleteCartItem(cartItemIds);
        session.removeAttribute("selectedProducts");
        return orderItemResult;
    }

}
