package com.lotteon.controller.apicontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotteon.dto.requestDto.PostBannerDTO;
import com.lotteon.dto.responseDto.GetBannerDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ApiConfigController {
    private final BannerService bannerService;

    @GetMapping("/banners/{tab}")
    public ResponseEntity<?> selectBanner(@PathVariable("tab") String tab) {
        int num = Integer.parseInt(tab);
        List<GetBannerDTO> bannerList = bannerService.findAllByCate(num);
        return ResponseEntity.ok().body(bannerList);
    }

    @PostMapping(value = {"/banners"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> insertBanner(@RequestPart("bannerDTO") String bannerJson,
                                          @RequestPart("file") MultipartFile file) {
        log.info("inserting...");
        ObjectMapper objectMapper = new ObjectMapper();
        PostBannerDTO bannerDTO;
        try {
            bannerDTO = objectMapper.readValue(bannerJson, PostBannerDTO.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Invaild Json Data: " + e.getMessage()));
        }
        try {
            bannerDTO.setUploadFile(file);
            Banner banner = bannerService.insert(bannerDTO);
            return ResponseEntity.ok().body(banner);
        } catch (Exception e) {
            log.error("Error inserting banner", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Failed to register banner: " + e.getMessage()));
        }
    }

    @PatchMapping("/banner/{id}/{state}")
    public ResponseEntity<?> changeBannerState(@PathVariable("id") Long id, @PathVariable("state") Integer state) {
        Banner banner = bannerService.updateBannerState(id, state);
        return ResponseEntity.ok().body(banner);
    }

    @DeleteMapping("/banners")
    public ResponseEntity<?> deleteBanner(@RequestBody List<Long> ids) {
        Boolean success = bannerService.deleteBannersById(ids);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/basic/{type}")
    public ResponseEntity<?> updateBanner(@RequestBody Banner banner) {
        return null;
    }

}
