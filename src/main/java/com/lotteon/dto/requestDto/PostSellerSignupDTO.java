package com.lotteon.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class PostSellerSignupDTO { // 판매회원 회원가입

    private Long memId; // 판매자 ID

    private String memPwd;

//    private String custName;
//
//    private String custGender;
//
//    private String custEmail;


    private int sellGrade; // 판매자 회원 등급

    private String sellCompany; // 회사명

    private String sellRepresentative; // 대표

    private String sellBusinessCode; // 사업자등록번호

    private String sellOrderCode; // 통신판매업번호

    private String sellHp; // 전화번호

    private String sellFax; // 팩스번호

    private String sellAddr; // 회사주소


}
