package com.lotteon.dto.requestDto;

import com.lotteon.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class PostRecruitDto {
    private Long id;

    private String title;

    private String department;

    private String career;

    private String type;

    private String sdate;

    private String edate;

    private String etc;

    private LocalDateTime rdate;

    private int state;

    private String memUid;
}
