package com.lotteon.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostBannerDTO {
    private String bannerName;
    private String bannerImg;
    private String bannerSize;
    private String bannerBg;
    private String bannerLink;
    private int bannerLocation;
    private Timestamp bannerSdate;
    private Timestamp bannerEdate;
    private Timestamp bannerStime;
    private Timestamp bannerEtime;

    private MultipartFile uploadFile;
}
