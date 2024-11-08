package com.lotteon.controller.controller;

import com.lotteon.dto.requestDto.cartOrder.PostCartSaveDto;
import com.lotteon.dto.responseDto.GetOption1Dto;
import com.lotteon.dto.responseDto.cartOrder.GetCartDto;
import com.lotteon.dto.responseDto.GetCategoryDto;
import com.lotteon.dto.responseDto.cartOrder.GetOrderDto;
import com.lotteon.dto.responseDto.cartOrder.GetCouponDto;
import com.lotteon.dto.responseDto.cartOrder.ResponseOrderDto;
import com.lotteon.dto.responseDto.cartOrder.UserOrderDto;
import com.lotteon.entity.product.Cart;
import com.lotteon.service.category.CategoryProductService;
import com.lotteon.service.member.CustomerService;
import com.lotteon.service.point.CustomerCouponService;
import com.lotteon.service.product.CartService;
import com.lotteon.service.product.OrderItemService;
import com.lotteon.service.product.OrderService;
import com.lotteon.service.product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/prod")
@RequiredArgsConstructor
@Log4j2
public class CartOrderController {

    private final CartService cartService;
    private final CategoryProductService categoryProductService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final CustomerCouponService customerCouponService;
    private final ProductService productService;

    @GetMapping("/cart")
    public String join(Model model, HttpServletRequest req,
                       HttpServletResponse resp , Authentication authentication) {
        Cart cart;
        if(authentication==null){
            cart = cartService.selectCartFornoAuth(req);
        }else{
            cart = cartService.selectCart(authentication, req,resp);
        }

        List<GetCartDto> cartItems = cartService.selectCartItem(cart);
        model.addAttribute("cartItems", cartItems);

        List<GetCategoryDto> category1 = categoryProductService.findCategory();

        Map<Long,List<GetOption1Dto>> options = cartService.selectOptions(cart);
        model.addAttribute("options", options);
        model.addAttribute("category1", category1);
        return "pages/product/cart";
    }

    @GetMapping("/cart/direct")
    public String cartDirect(Model model) {
        return "redirect:/prod/order";
    }

    @GetMapping("/order")
    public String order(Model model ,HttpSession session ) {
        List<GetCategoryDto> category1 = categoryProductService.findCategory();
        model.addAttribute("category1", category1);

        List<PostCartSaveDto> selectedProducts = (List<PostCartSaveDto>) session.getAttribute("selectedProducts");
        log.info("주문서에서 선택한 상품 "+selectedProducts.toString());

        UserOrderDto customer = customerService.selectedOrderCustomer();
        log.info("유저 정보 "+customer.toString());

        List<GetOrderDto> orders = orderService.selectedOrders(selectedProducts);
        log.info("오더 정보 "+orders.toString());
        model.addAttribute("orders", orders);
        model.addAttribute("customer", customer);
        List<Long> productIds = selectedProducts.stream().map(v->v.getProductId()).toList();
        List<GetCouponDto> coupons = customerCouponService.findByCustomerAndSeller(productIds);
        model.addAttribute("coupons",coupons);

        return "pages/product/order";
    }


    @GetMapping("/order/complete")
    public String orderComplete(Model model,HttpSession session) {

        List<Long> orderItemIds = (List<Long>) session.getAttribute("orderItemIds");
        log.info("오더아이템아이디 확인 "+orderItemIds);

        ResponseOrderDto order = orderItemService.selectedOrderComplete(orderItemIds);
        model.addAttribute("order", order);

        List<GetCategoryDto> category1 = categoryProductService.findCategory();

        model.addAttribute("category1", category1);
        session.removeAttribute("orderItemIds");
        return "pages/product/complete";
    }
}
