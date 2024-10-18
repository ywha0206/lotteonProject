package com.lotteon.service.member;

import com.lotteon.dto.requestDto.PostCustSignupDTO;
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

    public void insertCustomer(PostCustSignupDTO postCustSignupDTO) {
        try {
            log.info("PostCustSignupDTO : " + postCustSignupDTO);

            // 비밀번호 암호화
//            String encoded = passwordEncoder.encode(postCustSignupDTO.getMemberPw());
//            postCustSignupDTO.setMemberPw(encoded);
//
//            // 데이터베이스 저장 (Repository 사용)
//            userRepository.save(postCustSignupDTO.toEntity());

            log.info("사용자가 성공적으로 등록되었습니다: " + postCustSignupDTO.getCustEmail());
        } catch (Exception e) {
            // 예외 발생 시 로그 출력 및 에러 처리
            log.error("사용자 등록 중 오류가 발생했습니다.: ", e);

            // 필요한 경우 사용자에게 에러 정보를 리턴하거나 예외를 다시 던질 수 있습니다.
            throw new RuntimeException("다시 시도해 주세요.");
        }
    }




}
