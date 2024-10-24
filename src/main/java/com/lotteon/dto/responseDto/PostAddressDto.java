package com.lotteon.dto.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAddressDto {
    private String addrNick;
    private String addr;
    private String request;
    private Boolean basicState;
    private Long id;
}
