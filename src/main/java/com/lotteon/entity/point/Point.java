package com.lotteon.entity.point;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cust_id")
    private Long custId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "point_type")
    private int pointType;

    @Column(name = "point_rdate")
    @CreationTimestamp
    private Timestamp pointRdate;

    @Column(name = "point_var")
    private int pointVar;

    @Column(name = "point_etc")
    private String pointEtc;

    @Column(name = "point_expiration")
    private Timestamp pointExpiration;
}
