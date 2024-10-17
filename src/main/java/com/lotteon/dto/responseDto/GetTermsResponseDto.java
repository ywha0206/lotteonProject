package com.lotteon.dto.responseDto;

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

}
