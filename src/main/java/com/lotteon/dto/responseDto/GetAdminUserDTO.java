package com.lotteon.dto.responseDto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class GetAdminUserDTO {
    // 번호, 아이디, 이름, 성별, 등급, 포인트, 이메일, 휴대폰, 가입일, 상태, 관리
    
    private Long id;                // 번호
    private String  memUid;          // 아이디
    private String  custName;       // 이름

    private String custGrade;      // 사용자 등급
    private Boolean custGender;     // 성별
    private Integer custPoint;      // 포인트 (회원가입 축하포인트 default = "0")

    private String  custEmail;      // 이메일
    private String  custHp;         // 휴대폰 번호

    @CreationTimestamp
    private Timestamp memRdate;     // 가입일
    private String memState;        // 계정 상태 (활성, 비활성) [로그인, 로그아웃]





}

