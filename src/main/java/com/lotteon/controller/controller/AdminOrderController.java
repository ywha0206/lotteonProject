package com.lotteon.controller.controller;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.responseDto.cartOrder.ResponseOrdersDto;
import com.lotteon.entity.member.Customer;
import com.lotteon.service.product.OrderItemService;
import com.lotteon.service.product.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
@Log4j2
public class AdminOrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @ModelAttribute
    public void pageIndex(Model model) {
        model.addAttribute("config",getSideValue());
    }
    private String getSideValue() {
        return "order";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/orders")
    public String orders(Model model,
                         @RequestParam(name = "page",defaultValue = "0") int page) {
        orderService.selectedAdminOrders(page);

//        model.addAttribute("orders", orders);
//        model.addAttribute("page", page);
//        model.addAttribute("totalPages", orders.getTotalPages());
        model.addAttribute("active","orders");
        return "pages/admin/order/list";
    }

    @GetMapping("/deliverys")
    public String deliverys(Model model) {
        model.addAttribute("active","deliverys");
        return "pages/admin/order/delivery";
    }
}
