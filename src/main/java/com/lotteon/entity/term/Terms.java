package com.lotteon.entity.term;

import jakarta.persistence.*;
import lombok.*;

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

}
