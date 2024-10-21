package com.lotteon.controller.apicontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotteon.dto.requestDto.PatchConfigDTO;
import com.lotteon.dto.requestDto.PostBannerDTO;
import com.lotteon.dto.responseDto.GetBannerDTO;
import com.lotteon.entity.config.Banner;
import com.lotteon.entity.config.Config;
import com.lotteon.service.config.BannerService;
import com.lotteon.service.config.ConfigService;
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
    private final ConfigService configService;

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
                    .body(Collections.singletonMap("error", "Invalid  Json Data: " + e.getMessage()));
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

    @PatchMapping(value = {"/basic/site"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateBanner(@RequestPart("patch") String patchJson,
                                          @RequestPart(value = "file1" , required = false) MultipartFile file1,
                                          @RequestPart(value = "file2" , required = false) MultipartFile file2,
                                          @RequestPart(value = "file3" , required = false) MultipartFile file3)
    {

        log.info("updateConfig...");
        ObjectMapper objectMapper = new ObjectMapper();
        PatchConfigDTO patchConfigDTO;
        try {
            patchConfigDTO = objectMapper.readValue(patchJson, PatchConfigDTO.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Invalid Json Data: " + e.getMessage()));
        }
        log.info("patchData : {}" , patchConfigDTO);

        List<MultipartFile> files = null;
        files.add(file1);
        files.add(file2);
        files.add(file3);
        if(patchConfigDTO.getType()==3){patchConfigDTO.setFilesColumn(files);}

        Config config = configService.updateConfig(patchConfigDTO);

        return ResponseEntity.ok().body(config);
    }

}
