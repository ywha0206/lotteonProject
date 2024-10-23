package com.lotteon.entity.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance_event")
public class AttendanceEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attendance_days")
    private int attendanceDays;

    @Column(name = "attendance_sequence")
    private int attendanceSequence;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private Customer customer;

    public void updateEventReset(){
        this.attendanceDays = 1;
        this.attendanceSequence = 1;
    }

    public void updateEventIncrement(){
        this.attendanceDays++;
        this.attendanceSequence = 1;
    }

    public void updateEventComplete(){
        this.attendanceSequence = 2;
    }
}
