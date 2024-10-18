package com.lotteon.dto.responseDto;

import com.lotteon.entity.term.Terms;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class GetTermsResponseDto {

    private Long id;
    private String termsName;
    private String termsContent;
    private String termsType;

    public Terms toEntity() {
        return Terms.builder()
                .id(id)
                .termsName(termsName)
                .termsContent(termsContent)
                .termsType(termsType)
                .build();
    }
}
