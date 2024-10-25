package com.lotteon.controller.controller;

import com.lotteon.dto.responseDto.GetAdminUserDTO;
import com.lotteon.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/admin/user")
@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminUserController {

    private final AuthService authService;

    private String getSideValue() {
        return "user";  // 실제 config 값을 여기에 설정합니다.
    }

    // 1. 관리자 회원목록
    @GetMapping("/user")
    public String user(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "searchType", defaultValue = "0") String searchType,
            @RequestParam(name = "keyword", defaultValue = "0") String keyword

    ) {
        // 1. 회원 목록 customers에 담기
        List<GetAdminUserDTO> customers = authService.selectCustAll();
        log.info("민힁"+customers);

        Page<GetAdminUserDTO> cust2 = authService.selectCustAll2(page);

        // 2. 회원목록 모델에 담아서 뷰에서 보기
        model.addAttribute("customers", cust2);
        model.addAttribute("config", getSideValue());

        // 3. 페이지네이션

        model.addAttribute("page",page);
//        model.addAttribute("totalPages",customers.getTotalPages());
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages",cust2.getTotalPages());
        // 6. 관리자 회원목록 으로 이동
        return "pages/admin/user/user";
    }

    // 2. 관리자 포인트 관리
    @GetMapping("/point")
    public String point(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/user/point";
    }
}
