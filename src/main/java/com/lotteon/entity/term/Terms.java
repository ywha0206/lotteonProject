package com.lotteon.entity.term;

import com.lotteon.dto.responseDto.GetTermsResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "terms")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "terms_name")
    private String termsName;

    @Column(name = "terms_content", columnDefinition = "TEXT")
    private String termsContent;

    @Column(name = "terms_type")
    private String termsType;


    // Entity -> DTO
    public GetTermsResponseDto toDTO(){
        return GetTermsResponseDto.builder()
                .id(id)
                .termsName(termsName)
                .termsContent(termsContent)
                .termsType(termsType)
                .build();
    }

}
