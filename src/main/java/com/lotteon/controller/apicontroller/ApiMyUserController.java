package com.lotteon.controller.apicontroller;

import java.net.URI;
import java.util.HashMap;

import com.lotteon.dto.requestDto.MyInfoPassDto;
import com.lotteon.service.member.MemberService;
import jakarta.servlet.http.HttpSession; // javax 대신 jakarta 패키지 사용
import java.util.Map;
import com.lotteon.dto.requestDto.PatchMyInfoDTO;
import com.lotteon.service.member.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
@Log4j2
public class ApiMyUserController {

    private final CustomerService customerService;
    private final MemberService memberService;

    // 나의 설정 정보 수정
//    @PatchMapping("/info/{type}")
//    public ResponseEntity<?> modifyInfo(@PathVariable("type") String type,
//                                        @RequestBody PatchMyInfoDTO patchMyInfoDTO,
//                                        HttpSession session) {
//
//        Boolean success = customerService.modifyInfo(type, patchMyInfoDTO);
//
//        // 특정 조건에 따라 세션을 무효화합니다. 예: type이 "password"일 때 세션 무효화
//        if ("password".equals(type)) { // 비밀번호가 변경된 경우에만 세션을 무효화합니다.
//            session.invalidate(); // 세션 무효화
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", success);
//
//        return ResponseEntity.ok(response);
//    }


    @PostMapping("/info/pass2")
    public ResponseEntity<?> modifyPass(@RequestBody MyInfoPassDto myInfoPassDto,HttpSession session) {
        System.out.println("================================");
        log.info("컨트롤러 접속 "+myInfoPassDto);
        String pass = myInfoPassDto.getWhy();
        Long memId = myInfoPassDto.getMemId();

        Boolean result = memberService.updateMyPwd(pass, memId);
        if(result){
            session.invalidate();
            return ResponseEntity.ok().body("true");
        }
        return ResponseEntity.ok().body("false");
    }


}
