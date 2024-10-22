package com.lotteon.dto.responseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetConfigDTO {
    private Long id;

    private String configVersion;

    private String configTitle;

    private String configSub;

    private String configHeaderLogo;

    private String configFooterLogo;

    private String configFabicon;

    private int configUpdateVersion;

    private int configUpdateLocation;

    private String configUpdatedAdmin;

    private Timestamp configCreatedAt;

    private boolean configIsUsed;
}
