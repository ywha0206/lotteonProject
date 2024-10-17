package com.lotteon.dto.responseDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class GetCategoryDto {
    private Long id;
    private String name;

}
