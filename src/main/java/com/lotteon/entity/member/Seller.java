package com.lotteon.entity.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mem_id")
    private Long memId;

    @Column(name = "sell_grade")
    private int sellGrade;

    @Column(name = "sell_company")
    private String sellCompany;

    @Column(name = "sell_representative")
    private String sellRepresentative;

    @Column(name = "sell_business_code")
    private String sellBusinessCode;

    @Column(name = "sell_order_code")
    private String sellOrderCode;

    @Column(name = "sell_hp")
    private String sellHp;

    @Column(name = "sell_fax")
    private String sellFax;

    @Column(name = "sell_addr")
    private String sellAddr;
}
