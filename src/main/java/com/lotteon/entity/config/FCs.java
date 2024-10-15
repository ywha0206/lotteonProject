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

    @Column(name = "cs_hp")
    private String hp;

    @Column(name = "cs_rtime")
    private String rtime;

    @Column(name = "cs_etime")
    private String etime;

    @Column(name = "cs_email")
    private String email;

    @Column(name = "cs_name")
    private String name;

}
