package com.lotteon.entity.config;


import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`version`")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ver_name")
    private String verName;

    @Column(name = "ver_content", columnDefinition = "TEXT")
    private String verContent;

}
