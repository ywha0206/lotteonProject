package com.lotteon.service.member;

import com.lotteon.dto.requestDto.PostCustSignupDTO;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.member.Member;
import com.lotteon.repository.member.MemberRepository;
import com.lotteon.repository.term.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;


    public void insertMember(PostCustSignupDTO dto) {
//        Member member = Member.builder()
//                .memUid(dto.getMemId())
//                .memPwd(dto.getMemPwd())
//                .memRole("customer")
//                .build();
//
//        memberRepository.save(member);
//
//        Customer customer = Customer.builder()
//                .custName(dto.getCustName())
//                .custEmail(dto.getCustEmail())
//
//                .build();
//
//

    }
}
