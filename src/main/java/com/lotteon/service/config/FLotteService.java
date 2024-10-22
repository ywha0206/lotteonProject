package com.lotteon.service.config;

import com.lotteon.dto.requestDto.PostFLotteDTO;
import com.lotteon.dto.responseDto.GetFLotteDTO;
import com.lotteon.entity.config.FLotte;
import com.lotteon.repository.config.FLotteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FLotteService {
    private final ModelMapper modelMapper;
    private final FLotteRepository fLotteRepository;

    public FLotte updateFooter(PostFLotteDTO footerDTO) {
        FLotte fLotte = modelMapper.map(footerDTO, FLotte.class);
        return fLotteRepository.save(fLotte);
    }
    public GetFLotteDTO getRecentFLotte () {
        FLotte fLotte = fLotteRepository.findTopByOrderByIdDesc();
        return modelMapper.map(fLotte, GetFLotteDTO.class);
    }
}
