package com.lotteon.entity.member;

import com.lotteon.dto.responseDto.GetAdminUserDTO;
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

    @Column(name = "mem_uid")
    private String memUid;

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

    @OneToOne(mappedBy = "member")
    private Customer customer;

    @OneToOne(mappedBy = "member")
    private Seller seller;

    public GetAdminUserDTO toGetAdminUserDTO() {
        return GetAdminUserDTO.builder()
                .id(customer.getId()) // 번호
                .memUid(String.valueOf(customer.getMember().getMemUid())) // 아이디
                .custName(customer.getCustName()) // 이름
                .custGender(customer.getCustGender()) // 성별
                .custGrade(customer.getCustGrade()) // 등급
                .custPoint(customer.getCustPoint()) // 포인트
                .custEmail(customer.getCustEmail()) // 이메일
                .custHp(customer.getCustHp()) // 휴대폰
                .memRdate(memRdate) // 가입일
                .memState(String.valueOf(memState))
                .build();

    }
}
