package com.lotteon.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBannerDTO {
    private Long id;
    private String bannerImg;
    private String bannerName;
    private String bannerSize;
    private String bannerBg;
    private String bannerLink;
    private int bannerLocation;
    private Timestamp bannerSdate;
    private Timestamp bannerEdate;
    private Timestamp bannerStime;
    private Timestamp bannerEtime;
    private int bannerState;
}
