package com.lotteon.entity.config;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "banner_img")
    private String bannerImg;

    @Column(name = "banner_name")
    private String bannerName;

    @Column(name = "banner_size")
    private String bannerSize;

    @Column(name = "banner_bg")
    private String bannerBg;

    @Column(name = "banner_link")
    private String bannerLink;

    @Column(name = "banner_location")
    private int bannerLocation;

    @Column(name = "banner_sdate")
    private Timestamp bannerSdate;

    @Column(name = "banner_edate")
    private Timestamp bannerEdate;

    @Column(name = "banner_stime")
    private Timestamp bannerStime;

    @Column(name = "banner_etime")
    private Timestamp bannerEtime;

    @Column(name = "banner_state")
    private int bannerState;
}
