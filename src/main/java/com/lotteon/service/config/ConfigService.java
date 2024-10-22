package com.lotteon.service.config;

import com.lotteon.dto.requestDto.PatchConfigDTO;
import com.lotteon.dto.requestDto.PatchLogoDTO;
import com.lotteon.dto.responseDto.GetConfigDTO;
import com.lotteon.entity.config.Config;
import com.lotteon.repository.config.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConfigService {
    private final ConfigRepository configRepository;
    private final ImageService imageService;
    private final ModelMapper modelMapper;


    public GetConfigDTO getUsedConfig() {
        Optional<Config> opt = configRepository.findByConfigIsUsed(true);
        if (opt.isPresent()) {
            Config config = opt.get();
            return modelMapper.map(config, GetConfigDTO.class);
        }
        return null;
    }

    public Config updateInfo(PatchConfigDTO configDTO){
        Config existingConfig = configRepository.findById(configDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Config not found"));

        Config newConfig = existingConfig.copyConfig();
        newConfig.patchSiteInfo(configDTO.getTitle(), configDTO.getSub());
        newConfig.update(configDTO.getUpdater());

        return configRepository.save(newConfig);
    }

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

}
