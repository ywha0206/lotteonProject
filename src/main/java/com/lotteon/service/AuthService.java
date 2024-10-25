package com.lotteon.service;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.responseDto.GetAdminUserDTO;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.member.Member;
import com.lotteon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optUser = memberRepository.findByMemUid(username);

        if(optUser.isPresent()) {
            // 시큐리티 사용자 인증객체 생성 후 반환
            return MyUserDetails.builder()
                    .user(optUser.get())
                    .build();
        }


        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public List<GetAdminUserDTO> selectCustAll() {
        String role = "customer";
        List<Member> customers = memberRepository.findAllByMemRole(role);
        log.info("커스터머 서비스 : "+customers);

        // List<GetAdminUserDTO> cust = customers.stream().map(customer -> modelMapper.map(customer, GetAdminUserDTO.class)).toList();
        List<GetAdminUserDTO> cust = new ArrayList<>();
        customers.forEach(customer -> {
            // 번호, 아이디, 이름, 성별, 등급, 포인트, 이메일, 휴대폰, 가입일, 상태, 관리
            if (customer.getCustomer() != null) {
                GetAdminUserDTO dto = GetAdminUserDTO.builder()
                        .id(customer.getId()) // 번호
                        .memUid(String.valueOf(customer.getMemUid())) // 아이디
                        .custName(customer.getCustomer().getCustName()) // 이름
                        .custGender(customer.getCustomer().getCustGender()) // 성별
                        .custGrade(customer.getCustomer().getCustGrade()) // 등급
                        .custPoint(customer.getCustomer().getCustPoint()) // 포인트
                        .custEmail(customer.getCustomer().getCustEmail()) // 이메일
                        .custHp(customer.getCustomer().getCustHp()) // 휴대폰
                        .memRdate(customer.getMemRdate()) // 가입일
                        .memState(String.valueOf(customer.getMemState())) // 상태
                        .build();
                cust.add(dto);
            } else {
                // null일 때 처리 방법
                // 1. 기본값으로 처리 (예시)


                // 2. 혹은 생략할 수도 있습니다. (이 경우 아무 작업도 하지 않음)
                // log.warn("Customer 정보가 없는 회원 ID: " + customer.getId());
            }
        });

        return cust;

    }


    public Page<GetAdminUserDTO> selectCustAll2(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Member> members = memberRepository.findAllByMemRoleOrderByIdDesc("customer",pageable);
        Page<GetAdminUserDTO> dtos = members.map(v->v.toGetAdminUserDTO());

        return dtos;
    }
}
