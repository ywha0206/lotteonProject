package com.lotteon.dto.responseDto;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetVersionDTO {
    private Long id;

    private Long mem_id;

    private String verName;

    private String verContent;

    private Timestamp verRdate;

}
