package com.lotteon.controller.controller;

import com.lotteon.config.MyUserDetails;
import com.lotteon.entity.member.AttendanceEvent;
import com.lotteon.entity.member.Customer;
import com.lotteon.repository.member.AttendanceEventRepository;
import com.lotteon.service.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/event")
@Controller
@RequiredArgsConstructor
@Log4j2
public class EventController {

    private final EventService eventService;
    private final AttendanceEventRepository attendanceEventRepository;

    @GetMapping("/attendance")
    public String attendance(Model model) {
        String result = eventService.updateEvent();
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Customer customer = auth.getUser().getCustomer();
        AttendanceEvent event = attendanceEventRepository.findByCustomer(customer);
        if(event.getAttendanceMiddleState()==1){
            model.addAttribute("middle",true);
        }
        if(event.getAttendanceState()==1){
            model.addAttribute("state",true);
        }
        int days = eventService.findEvent(result);
        model.addAttribute("days", days);
        return "pages/event/attendance";
    }

    @GetMapping("/birth")
    public String birth(Model model) {
        eventService.issueCoupon();
        model.addAttribute("birth", true);
        return "index";
    }
}
