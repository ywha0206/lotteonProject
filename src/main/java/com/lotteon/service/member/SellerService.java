package com.lotteon.service.member;

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

}
