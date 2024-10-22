package com.lotteon.service.config;

import com.lotteon.dto.requestDto.PostVersionDTO;
import com.lotteon.dto.responseDto.GetVersionDTO;
import com.lotteon.entity.config.Version;
import com.lotteon.repository.config.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
@Transactional
public class VersionService {
    private final VersionRepository versionRepository;
    private final ModelMapper modelMapper;

    public Version insertVersion(PostVersionDTO postVersionDTO) {
        Version version = modelMapper.map(postVersionDTO, Version.class);
        return versionRepository.save(version);
    }

    public Page<GetVersionDTO> getVersionList(int page) {

        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id").descending());
        Page<Version> versionPage = versionRepository.findAll(pageable);

        return versionPage.map(version -> modelMapper.map(version,GetVersionDTO.class));
    }
}
