package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.PostBannerDTO;
import com.lotteon.entity.config.Banner;
import com.lotteon.service.config.BannerService;
import com.lotteon.service.config.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ApiConfigController {
    private final BannerService bannerService;

    @PostMapping("/banner")
    public ResponseEntity<?> insertBanner(@ModelAttribute PostBannerDTO bannerDTO) {
        log.info("insert banner : ", bannerDTO);

        try {
            Banner banner = bannerService.insert(bannerDTO);
            return ResponseEntity.ok().body(banner);
        } catch (Exception e) {
            log.error("Error inserting banner", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Banner registration failed");
        }
    }
}
