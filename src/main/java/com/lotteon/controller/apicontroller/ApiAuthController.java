package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.PostFindIdDto;
import com.lotteon.service.AuthService;
import com.lotteon.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
*   이름 : 이상훈
*   날짜 : 202-10-28
*   작업내용 : 셀러 / 커스터머 아이디찾기 기능 구현
* */

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class ApiAuthController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send/{type}")
    public ResponseEntity<?> sendEmail(@PathVariable String type, @RequestBody PostFindIdDto dto) {
        if(type.equals("customer")){
            String result = authService.matchingCheckByNameAndEmail(dto.getName(),dto.getEmail());
            if(result.equals("NF")){
                return ResponseEntity.ok("NF");
            }
            String randomCode = String.format("%06d", (int)(Math.random() * 1000000));
            emailService.sendEmail(dto.getEmail(),dto.getName() +"님 (LotteOn)아이디찾기 인증코드입니다.",randomCode);
            return ResponseEntity.ok(randomCode);
        } else {
            String result = authService.matchingCheckByBusinessAndEmail(dto.getName(),dto.getEmail());
            if(result.equals("NF")){
                return ResponseEntity.ok("NF");
            }
            String randomCode = String.format("%06d", (int)(Math.random() * 1000000));
            emailService.sendEmail(dto.getEmail(),result +"님 (LotteOn)아이디찾기 인증코드입니다.",randomCode);
            return ResponseEntity.ok(randomCode);
        }

    }

}
