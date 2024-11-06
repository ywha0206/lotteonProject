package com.lotteon.controller.apicontroller;

import java.util.Map;
import com.lotteon.dto.requestDto.PatchMyInfoDTO;
import com.lotteon.service.member.CustomerService;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
@Log4j2
public class ApiMyUserController {

    private final CustomerService customerService;

    // 나의설정 정보 수정
//    @PutMapping("/info/{id}")
//    public ResponseEntity<GetMyInfoDTO> updateInfo(
//                        @PathVariable("id") Long id,
//                        @RequestBody GetMyInfoDTO getMyInfoDTO) {
//        GetMyInfoDTO updateInfo = customerService.updateInfo(id, getMyInfoDTO);
//        log.info("MyInfo 정보수정 아이디: "+id);
//        log.info("MyInfo 정보수정 디티오 : "+ getMyInfoDTO);
//
//        return ResponseEntity.ok().build(updateInfo);
//    }


    // 나의 설정 수정
    @PatchMapping("/info/{type}")
    public ResponseEntity<?> modifyInfo(@PathVariable("type") String type,
                            @RequestBody PatchMyInfoDTO patchMyInfoDTO) {

        Boolean success = customerService.modifyInfo(type, patchMyInfoDTO);
        //log.info("안녕 나의 설정 수정");
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", success);

        return ResponseEntity.ok(response);
    }





}
