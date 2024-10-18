package com.lotteon.controller.apicontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotteon.dto.requestDto.PostBannerDTO;
import com.lotteon.entity.config.Banner;
import com.lotteon.service.config.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ApiConfigController {
    private final BannerService bannerService;

    @PostMapping(value = {"/banners"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> insertBanner(@RequestPart("bannerDTO") String bannerJson,
                                          @RequestPart("file") MultipartFile file) {
        log.info("inserting...");


        ObjectMapper objectMapper = new ObjectMapper();
        PostBannerDTO bannerDTO;
        try {
            bannerDTO = objectMapper.readValue(bannerJson, PostBannerDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Invaild Json Data: " + e.getMessage()));
        }

        log.info("Inserted banner: {}", bannerDTO);

        try {
            bannerDTO.setUploadFile(file);
            // 서비스 호출하여 배너 등록
            Banner banner = bannerService.insert(bannerDTO);

            // 성공 응답
            return ResponseEntity.ok().body(banner);
        } catch (Exception e) {
            log.error("Error inserting banner", e);
            // 실패 시 Bad Request 응답
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Failed to register banner: " + e.getMessage()));
        }
    }
}
