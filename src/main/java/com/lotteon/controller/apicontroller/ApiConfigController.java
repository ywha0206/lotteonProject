package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.PostBannerDTO;
import com.lotteon.entity.config.Banner;
import com.lotteon.service.config.BannerService;
import com.lotteon.service.config.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ApiConfigController {
    private final ConfigService configService;
    private final BannerService bannerService;

    @PostMapping("/banner")
    public ResponseEntity<?> insertBanner(@RequestBody PostBannerDTO bannerDTO) {
        log.info("insert banner : ", bannerDTO);
        Banner banner = bannerService.insert(bannerDTO);
        return ResponseEntity.ok().body(banner);
    }
}
