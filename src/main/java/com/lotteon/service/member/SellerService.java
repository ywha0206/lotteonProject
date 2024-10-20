package com.lotteon.service.member;

import com.lotteon.dto.requestDto.PostAdminSellerDto;
import com.lotteon.entity.member.Member;
import com.lotteon.entity.member.Seller;
import com.lotteon.repository.member.MemberRepository;
import com.lotteon.repository.member.SellerRepository;
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
public class SellerService {

    private final PasswordEncoder passwordEncoder;
    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final SellerRepository sellerRepository;

    public void postSeller(PostAdminSellerDto postAdminSellerDto) {

        Member member = Member.builder()
                .memPwd(passwordEncoder.encode(postAdminSellerDto.getMemPwd()))
                .memUid(postAdminSellerDto.getMemId())
                .memRole("seller")
                .memState("basic")
                .build();

        memberRepository.save(member);

        Seller seller = Seller.builder()
                .member(member)
                .sellAddr(postAdminSellerDto.getSellAddr())
                .sellCompany(postAdminSellerDto.getSellCompany())
                .sellFax(postAdminSellerDto.getSellFax())
                .sellBusinessCode(postAdminSellerDto.getSellCode())
                .sellOrderCode(postAdminSellerDto.getSellOrderCode())
                .sellRepresentative(postAdminSellerDto.getSellRep())
                .sellHp(postAdminSellerDto.getSellHp())
                .build();

        sellerRepository.save(seller);

    }
}
