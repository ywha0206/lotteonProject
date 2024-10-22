package com.lotteon.controller.controller;

import com.lotteon.dto.responseDto.GetBannerDTO;
import com.lotteon.dto.responseDto.GetConfigDTO;
import com.lotteon.service.config.BannerService;
import com.lotteon.service.config.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/config")
@RequiredArgsConstructor
@Log4j2
public class AdminConfigController {
    private final BannerService bannerService;
    private final ConfigService configService;

    private String getSideValue() {
        return "config";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/index";
    }
    @GetMapping("/basics")
    public String basic(Model model) {
        GetConfigDTO ConfigDTO = configService.getUsedConfig();
        model.addAttribute("config", getSideValue());
        model.addAttribute("site", ConfigDTO);
        return "pages/admin/config/basic";
    }
    @GetMapping("/banners")
    public String banner(Model model) {
        List<GetBannerDTO> bannerList = bannerService.findAllByCate(1);
        model.addAttribute("bannerList", bannerList);
        model.addAttribute("config", getSideValue());
        return "pages/admin/config/banner";
    }
    @GetMapping("/terms")
    public String terms(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/config/term";
    }
    @GetMapping("/versions")
    public String version(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/config/version";
    }


}
