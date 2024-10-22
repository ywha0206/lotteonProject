package com.lotteon.service.member;

import com.lotteon.dto.requestDto.PostCustSignupDTO;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.member.Member;
import com.lotteon.entity.member.Seller;
import com.lotteon.repository.member.CustomerRepository;
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
public class CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void insertCustomer(PostCustSignupDTO postCustSignupDTO) {
        try {

            // Member 객체 생성 및 저장 (멤버 DB에 아이디, 비번 저장)
            Member member = Member.builder()
                    .memUid(postCustSignupDTO.getMemId())
                    .memPwd(passwordEncoder.encode(postCustSignupDTO.getMemPwd()))
                    .memRole("customer") // 기본 사용자 유형 "customer"
                    .memState("basic")   // 기본 계정 상태 "basic"
                    .build();

            memberRepository.save(member);
            // Addr1 + Addr2 + Addr3 = 부산광역시 + 부산진구 + 부전동
            String addr = postCustSignupDTO.getAddr1()+"/"+postCustSignupDTO.getAddr2()+"/"+postCustSignupDTO.getAddr3();

            // Customer 객체 생성 및 저장
            Customer customer = Customer.builder()
                    .member(member)
                    .custName(postCustSignupDTO.getCustName())
                    .custGender(postCustSignupDTO.getCustGender() != null ? postCustSignupDTO.getCustGender() : false)  // null일 때 false로 처리
                    .custEmail(postCustSignupDTO.getCustEmail())
                    .custHp(postCustSignupDTO.getCustHp())
                    .custAddr(postCustSignupDTO.getAddr1() + "/" + postCustSignupDTO.getAddr2() + "/" + postCustSignupDTO.getAddr3())
                    .custPoint(0)
                    .build();

            customerRepository.save(customer);

        } catch (Exception e) {
            // 예외 발생 시 로그 출력 및 에러 처리
            log.error("사용자 등록 중 오류가 발생했습니다.: ", e);

            // 필요한 경우 사용자에게 에러 정보를 리턴하거나 예외를 다시 던질 수 있습니다.
            throw new RuntimeException("다시 시도해 주세요.");
        }
    }




}
