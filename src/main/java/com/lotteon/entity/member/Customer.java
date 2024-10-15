package com.lotteon.entity.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mem_id")
    private Long memberId;

    @Column(name = "cust_point")
    private Integer custPoint;

    @Column(name = "cust_addr")
    private String custAddr;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_hp")
    private String custHp;

    @Column(name = "cust_gender")
    private Boolean custGender;

    @Column(name = "cust_email")
    private String custEmail;

    @Column(name = "cust_birth")
    private String custBirth;

    @Column(name = "cust_addr_option")
    private Boolean custAddrOption;

    @Column(name = "cust_term_option")
    private Boolean custTermOption;

}
