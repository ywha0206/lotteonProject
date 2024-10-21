package com.lotteon.dto.requestDto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchConfigDTO {
    private Long id;
    private int type;
    private String strColumn1;
    private String strColumn2;
    private List<MultipartFile> filesColumn;
    private String updater;
}
