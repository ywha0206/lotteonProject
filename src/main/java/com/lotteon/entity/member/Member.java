package com.lotteon.entity.member;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`member`")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mem_id")
    private String memId;

    @Column(name = "mem_pwd")
    private String memPwd;

    @Column(name = "mem_role")
    private String memRole;

    @Column(name = "mem_rdate")
    @CreationTimestamp
    private Timestamp memRdate;

    @Column(name = "mem_state")
    private String memState;

    @Column(name = "mem_edate")
    private Timestamp memSignout;
}
