package com.lotteon.service.config;

import com.lotteon.dto.requestDto.PostBannerDTO;
import com.lotteon.entity.config.Banner;
import com.lotteon.repository.config.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BannerService {
    private final BannerRepository bannerRepository;
    private final ModelMapper modelMapper;

    public Banner insert(PostBannerDTO bannerDTO) {
        log.info(bannerDTO.toString());
        Banner banner = modelMapper.map(bannerDTO, Banner.class);
        return bannerRepository.save(banner);
    }
}
