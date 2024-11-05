package com.lotteon.controller;

import com.lotteon.dto.responseDto.*;
import com.lotteon.service.config.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@ControllerAdvice
public class CommonController {

    private final BannerService bannerService;
    private final ConfigService configService;
    private final FLotteService flotteService;
    private final FCsService fcsService;
    private final CopyrightService copyrightService;

    @ModelAttribute
    public void getCommon(Model model, HttpServletRequest request) {

        GetConfigDTO ConfigDTO = configService.getUsedConfig();
        GetFLotteDTO fLotteDTO = flotteService.getRecentFLotte();
        GetFCsDTO    fCsDTO    = fcsService.getRecentFCs();
        GetCopyrightDTO copyrightDTO = copyrightService.getRecentCopyright();


        model.addAttribute("appName", ConfigDTO.getConfigTitle());
        model.addAttribute("appVersion", ConfigDTO.getConfigVersion());

        model.addAttribute("site", ConfigDTO);
        model.addAttribute("fLotte", fLotteDTO);
        model.addAttribute("fCs", fCsDTO);
        model.addAttribute("copy", copyrightDTO);

        List<GetBannerDTO> banners = null;

        List<String> targetUris = Arrays.asList("/", "/index");
        String name = "banner";
        if (targetUris.contains(request.getRequestURI())) {
            banners = bannerService.selectUsingBannerAt(1);
            name = "topBanner";
        }else if (request.getRequestURI().startsWith("/prod/product")) {
            banners = bannerService.selectUsingBannerAt(3);

        }else if (request.getRequestURI().startsWith("/auth/login/view")) {
            banners = bannerService.selectUsingBannerAt(4);

        }else if (request.getRequestURI().startsWith("/my")) {
            banners = bannerService.selectUsingBannerAt(5);

        }

        if (banners != null && !banners.isEmpty()) {
            if (banners.size() > 1) {
                Random random = new Random();
                int randomIndex = random.nextInt(banners.size());
                model.addAttribute(name, banners.get(randomIndex));
            } else {
                model.addAttribute(name, banners.get(0));
            }
        }
    }

}
