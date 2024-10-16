package com.lotteon.entity.config;


import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cs")
public class FCs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cs_hp1")
    private String hp1;

    @Column(name = "cs_hp2")
    private String hp2;

    @Column(name = "cs_time1")
    private String time1;

    @Column(name = "cs_time2")
    private String time2;

}
