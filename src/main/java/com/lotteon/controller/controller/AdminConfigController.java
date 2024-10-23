package com.lotteon.controller.controller;

import com.lotteon.dto.responseDto.*;
import com.lotteon.service.config.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/config")
@RequiredArgsConstructor
@Log4j2
public class AdminConfigController {
    private final BannerService bannerService;
    private final ConfigService configService;
    private final FLotteService flotteService;
    private final FCsService fcsService;
    private final CopyrightService copyrightService;
    private final VersionService versionService;

    private String getSideValue() {
        return "config";  // 실제 config 값을 여기에 설정합니다.
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/index";
    }
    @GetMapping("/basics")
    public String basic(Model model) {
        GetConfigDTO ConfigDTO = configService.getUsedConfig();
        GetFLotteDTO fLotteDTO = flotteService.getRecentFLotte();
        fLotteDTO.splitAddress();
        GetFCsDTO fCsDTO = fcsService.getRecentFCs();
        GetCopyrightDTO copyrightDTO = copyrightService.getRecentCopyright();

        model.addAttribute("config", getSideValue());
        model.addAttribute("site", ConfigDTO);
        model.addAttribute("fLotte", fLotteDTO);
        model.addAttribute("fCs", fCsDTO);
        model.addAttribute("copy", copyrightDTO);
        return "pages/admin/config/basic";
    }
    @GetMapping("/banners")
    public String banner(Model model) {
        List<GetBannerDTO> bannerList = bannerService.findAll();
        model.addAttribute("bannerList", bannerList);
        model.addAttribute("config", getSideValue());
        return "pages/admin/config/banner";
    }
    @GetMapping("/terms")
    public String terms(Model model) {
        model.addAttribute("config", getSideValue());
        return "pages/admin/config/term";
    }
    @GetMapping("/versions")
    public String version(Model model) {
        PageResponseDTO<GetVersionDTO> page = versionService.getPagedVersionList(1);
        model.addAttribute("resp", page);
        model.addAttribute("config", getSideValue());
        return "pages/admin/config/version";
    }
    @GetMapping("/versions/{pg}")
    public String version(@PathVariable(value = "pg") Integer pg, Model model) {
        PageResponseDTO<GetVersionDTO> page = versionService.getPagedVersionList(pg);
        model.addAttribute("resp", page);
        model.addAttribute("config", getSideValue());
        return "pages/admin/config/version";
    }


}
