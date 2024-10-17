package com.lotteon.service.term;

import com.lotteon.dto.responseDto.GetTermsResponseDto;
import com.lotteon.entity.term.Terms;
import com.lotteon.repository.term.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TermsService {

    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;


    // 1. 이용약관
    public GetTermsResponseDto selectTerms(String termsType) {
        System.out.println("22222" + termsType);
       List<Terms> terms = termsRepository.findAllByTermsType(termsType);
        System.out.println("333333" + terms);
       GetTermsResponseDto userMapDto = modelMapper.map(terms, GetTermsResponseDto.class);
        System.out.println(" 111111 "+userMapDto);
       return userMapDto;
    }




}

