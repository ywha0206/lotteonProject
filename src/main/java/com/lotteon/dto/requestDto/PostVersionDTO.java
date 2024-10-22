package com.lotteon.dto.requestDto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVersionDTO {
    private Long mem_id;
    private String verName;
    private String verContent;
}
