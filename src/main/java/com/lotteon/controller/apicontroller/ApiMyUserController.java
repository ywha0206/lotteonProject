package com.lotteon.controller.apicontroller;

import java.net.URI;
import java.util.HashMap;

import com.lotteon.dto.requestDto.MyInfoPassDto;
import com.lotteon.dto.responseDto.GetMyInfoDTO;
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
    @PatchMapping("/info/{type}")
    public ResponseEntity<?> modifyInfo(@PathVariable("type") String type,
                                        @RequestBody PatchMyInfoDTO patchMyInfoDTO,
                                        HttpSession session) {
        Long custId = patchMyInfoDTO.getCustId();
        String email = patchMyInfoDTO.getCustEmail();
        String hp = patchMyInfoDTO.getCustHp();

        log.info("컨트롤러 접속 "+type+"dto "+patchMyInfoDTO);

        Boolean success = false;
        if(type.equals("email")){
            success = customerService.updateCustomerEmail(custId, email);
        }else {
            success = customerService.updateCustomerHp(custId, hp);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        return ResponseEntity.ok(response);
    }


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
    @PostMapping("/info")
    public String updateMyinfo(GetMyInfoDTO getMyInfoDTO) {
        log.info("컨트롤러 접속 "+getMyInfoDTO);
        Boolean result = customerService.updateCustomer(getMyInfoDTO);
        if(result){
            return "redirect:/my/info";
        }else{
            return "redirect:/my/index";
        }
    }

}
