package com.lotteon.dto.requestDto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class PostSellerSignupDTO { // 판매회원 회원가입

    private String memId;               // 판매자 ID
    private String memPwd;              // 판매자 PW
    private int    sellGrade;           // 판매자 회원 등급
    private String sellCompany;         // 회사명
    private String sellRepresentative;  // 대표명
    private String sellBusinessCode;    // 사업자등록번호
    private String sellOrderCode;       // 통신판매업번호
    private String sellHp;              // 전화번호
    private String sellFax;             // 팩스번호
    private String sellAddr;            // 회사주소

}
