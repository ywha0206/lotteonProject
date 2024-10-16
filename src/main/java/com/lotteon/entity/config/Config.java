package com.lotteon.entity.config;


import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "config")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version_id")
    private Long configVersion;

    @Column(name = "config_title")
    private String configTitle;

    @Column(name = "config_sub")
    private String configSub;

    @Column(name = "config_header_logo")
    private String configHeaderLogo;

    @Column(name = "config_footer_logo")
    private String configFooterLogo;

    @Column(name = "config_fabicon")
    private String configFabicon;


}
