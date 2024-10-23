package com.lotteon.repository.member;

import com.lotteon.entity.member.AttendanceEvent;
import com.lotteon.entity.member.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceEventRepository extends JpaRepository<AttendanceEvent, Long> {
    AttendanceEvent findByCustomer(Customer customer);
}
