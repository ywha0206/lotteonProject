package com.lotteon.service.config;

import com.lotteon.dto.requestDto.PatchConfigDTO;
import com.lotteon.entity.config.Config;
import com.lotteon.repository.config.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConfigService {
    private final ConfigRepository configRepository;
    private final ImageService imageService;

    public Config updateConfig(PatchConfigDTO patchDTO) {
        Config existingConfig = configRepository.findById(patchDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Config not found"));

        Config newConfig = existingConfig;

        if(patchDTO.getType()==1){ //siteVersion
            newConfig.patchSiteVersion(patchDTO.getStrColumn1());
        }else if(patchDTO.getType()==2) { //siteInfo
            newConfig.patchSiteInfo(patchDTO.getStrColumn1(), patchDTO.getStrColumn2());
        }else if(patchDTO.getType()==3) { // logo
            List<String> filename = imageService.uploadImages(patchDTO.getFilesColumn());
            newConfig.patchSiteLogo(filename.get(0),filename.get(1),filename.get(2));
        }
        newConfig.update(patchDTO.getUpdater());

        return configRepository.save(newConfig);
    }
}
