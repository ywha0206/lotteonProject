package com.lotteon.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCustSignupDTO { // post customer 일반회원 회원가입

    private String memId; // 아이디
    private String memPwd; // 비밀번호
    private String custName; // 이름
    private String custGender; // 성별 
    private String custEmail; // 이메일
    private String custHp; // 휴대폰 번호
    private String addr1; // 주소
    private String addr2; // 주소
    private String addr3; // 주소
    private Integer basicAddr; // 배송주소 설정 여부
    private Boolean custOptinal ; // 선택약관 동의 여부


}
