package com.lotteon.service.config;

import com.lotteon.dto.requestDto.PatchConfigDTO;
import com.lotteon.dto.requestDto.PatchLogoDTO;
import com.lotteon.dto.responseDto.GetBannerDTO;
import com.lotteon.dto.responseDto.GetConfigDTO;
import com.lotteon.dto.responseDto.GetConfigListDTO;
import com.lotteon.entity.config.Config;
import com.lotteon.repository.config.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ConfigService {
    private final ConfigRepository configRepository;
    private final ImageService imageService;
    private final ModelMapper modelMapper;


    @Cacheable(value = "configCache", key = "'config'", cacheManager = "cacheManager")
    public GetConfigDTO getUsedConfig() {
        Optional<Config> opt = configRepository.findByConfigIsUsed(true);
        if (opt.isPresent()) {
            Config config = opt.get();
            GetConfigDTO dto = modelMapper.map(config, GetConfigDTO.class);
            dto.setCreatedStr(); // 생성 후 설정 메서드 호출
            return dto;
        }
        return null;
    }

    @Caching(evict = {  @CacheEvict(value = "configCache", key = "'configList'"),
                        @CacheEvict(value = "configCache", key = "'config'")})
    public Config updateInfo(PatchConfigDTO configDTO){
        Config existingConfig = configRepository.findById(configDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Config not found"));

        Config newConfig = existingConfig.copyConfig();
        newConfig.patchSiteInfo(configDTO.getTitle(), configDTO.getSub());
        newConfig.update(configDTO.getUpdater());

        return configRepository.save(newConfig);
    }

    @Caching(evict = {  @CacheEvict(value = "configCache", key = "'configList'"),
                        @CacheEvict(value = "configCache", key = "'config'")})
    public Config updateLogo(PatchLogoDTO logoDTO) {
        Config existingConfig = configRepository.findById(logoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Config not found"));

        String file1name = imageService.uploadImage(logoDTO.getFile1());
        String file2name = imageService.uploadImage(logoDTO.getFile2());
        String file3name = imageService.uploadImage(logoDTO.getFile3());

        Config newConfig = existingConfig.copyConfig();
        newConfig.patchSiteLogo(file1name,file2name,file3name);
        newConfig.update(logoDTO.getUpdater());

        return configRepository.save(newConfig);
    }


    @Cacheable(value = "configCache", key = "'configList'", cacheManager = "cacheManager")
    public List<GetConfigListDTO> getRecentConfigs() {
        List<Config> configs = configRepository.findTop10ByOrderByConfigCreatedAtDesc();

        return configs.stream()
                .map(entity -> {
                    GetConfigListDTO dto = modelMapper.map(entity, GetConfigListDTO.class);
                    dto.setCreatedStr(); // 변환 메서드 호출
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Cacheable(value = "configCache", key = "#id", cacheManager = "cacheManager")
    public GetConfigDTO getConfigById(Long id) {
        Optional<Config> opt = configRepository.findById(id);
        if (opt.isPresent()) {
            Config config = opt.get();
            GetConfigDTO dto = modelMapper.map(config, GetConfigDTO.class);
            dto.setCreatedStrDetail();
            return dto;
        }
        return null;
    }
}
