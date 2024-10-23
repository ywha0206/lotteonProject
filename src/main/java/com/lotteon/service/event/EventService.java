package com.lotteon.service.event;

import com.lotteon.config.MyUserDetails;
import com.lotteon.entity.member.AttendanceEvent;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.point.Point;
import com.lotteon.repository.member.AttendanceEventRepository;
import com.lotteon.repository.member.CustomerRepository;
import com.lotteon.repository.point.PointRepository;
import com.lotteon.service.member.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final AttendanceEventRepository attendanceEventRepository;
    private final PointRepository pointRepository;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public String updateEvent() {
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Customer customer = auth.getUser().getCustomer();

        AttendanceEvent event = attendanceEventRepository.findByCustomer(customer);
        if (event == null) {
            event = this.insertEvent(event,customer);
            attendanceEventRepository.save(event);
        }
        if(event.getAttendanceSequence()==2){
            return "complete";
        }
        if(event.getAttendanceDays()==7){
            event.updateEventComplete();
            attendanceEventRepository.save(event);
        }

        if(event.getAttendanceSequence()==1){
            event.updateEventReset();
            attendanceEventRepository.save(event);
        } else if(event.getAttendanceSequence()==0){
            event.updateEventIncrement();
            attendanceEventRepository.save(event);
        }
        return "advance";

    }





    private AttendanceEvent insertEvent(AttendanceEvent event,Customer customer) {
        return event = AttendanceEvent.builder()
                .attendanceSequence(0)
                .customer(customer)
                .attendanceDays(0)
                .build();
    }


    public int findEvent(String result) {
        if(result.equals("complete")){
            this.updateCustomerPoint();
            return 7;
        } else {

            return 0;
        }

    }

    private void updateCustomerPoint() {
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        LocalDate today = LocalDate.now().plusMonths(2);

        Customer customer = auth.getUser().getCustomer();

        Point point = Point.builder()
                .pointVar(100)
                .pointType(1)
                .pointEtc("출석이벤트")
                .custId(customer.getId())
                .pointExpiration(today)
                .build();
        pointRepository.save(point);

        int points = customerService.updateCustomerPoint(customer);
        customer.updatePoint(points);
        customerRepository.save(customer);
    }
}
