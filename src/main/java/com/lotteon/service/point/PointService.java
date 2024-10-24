package com.lotteon.service.point;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.responseDto.GetPointsDto;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.point.Point;
import com.lotteon.repository.member.CustomerRepository;
import com.lotteon.repository.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {
    private final PointRepository pointRepository;
    private final CustomerRepository customerRepository;

    public Page<GetPointsDto> findAllByCustomer(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Customer customer = auth.getUser().getCustomer();

        Page<Point> points = pointRepository.findAllByCustomerOrderByPointExpiration(customer,pageable);
        Page<GetPointsDto> dtos = points.map(v->v.toGetPointsDto());

        return dtos;
    }

    public Page<GetPointsDto> findAllBySearch(int page, String type, String keyword) {
        Pageable pageable = PageRequest.of(page, 5);
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Customer customer = auth.getUser().getCustomer();
        Page<Point> points;
        if(type.equals("date")){
            points = this.findAllByDate(pageable,customer,keyword);
        } else if(type.equals("custom")){
            points = this.findAllByCustom(pageable,customer,keyword);
        }else {
            points = this.findAllByMonth(pageable,customer,keyword);
        }
        Page<GetPointsDto> dtos = points.map(v->v.toGetPointsDto());
        return dtos;
    }

    private Page<Point> findAllByCustom(Pageable pageable, Customer customer, String keyword) {

        String sDate = keyword.substring(0,keyword.indexOf("~"));
        String eDate = keyword.substring(keyword.indexOf("~")+1);
        LocalDate startDate = LocalDate.parse(sDate);
        LocalDate endDate = LocalDate.parse(eDate);
        Page<Point> points = pointRepository.findAllByCustomerAndPointRdateBetweenOrderByPointExpirationAsc(customer,startDate,endDate,pageable);
        return points;
    }

    private Page<Point> findAllByMonth(Pageable pageable, Customer customer, String keyword) {
        LocalDate today = LocalDate.now();
        LocalDate varDay = today.minusMonths(Integer.parseInt(keyword));
        Page<Point> points = pointRepository.findAllByCustomerAndPointRdateBetweenOrderByPointExpirationAsc(customer,varDay,today,pageable);
        return points;
    }

    private Page<Point> findAllByDate(Pageable pageable, Customer customer, String keyword) {
        LocalDate today = LocalDate.now();
        LocalDate varDay = today.minusDays(Integer.parseInt(keyword));
        Page<Point> points = pointRepository.findAllByCustomerAndPointRdateBetweenOrderByPointExpirationAsc(customer,varDay,today,pageable);
        return points;
    }

    public Page<GetPointsDto> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 7);

        Page<Point> points = pointRepository.findAllByOrderById(pageable);
        Page<GetPointsDto> dtos = points.map(Point::toGetPointsDtoCustName);

        return dtos;
    }

    public Page<GetPointsDto> findAllByAdminSearch(int page, String searchType, String keyword) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Point> points;

        if(searchType.equals("uid")){
            points = this.findPointByUid(keyword,pageable);
        } else if(searchType.equals("name")){
            points = this.findPointByCustName(keyword,pageable);
        } else if(searchType.equals("email")){
            points = this.findPointByCustEmail(keyword,pageable);
        } else {
            points = this.findPointByCustHp(keyword,pageable);
        }
        Page<GetPointsDto> dtos = points.map(Point::toGetPointsDtoCustName);
        return dtos;
    }

    private Page<Point> findPointByCustHp(String keyword, Pageable pageable) {
        Customer customer = customerRepository.findByCustHp(keyword);
        Page<Point> points = pointRepository.findAllByCustomer_CustHp(customer.getCustHp(),pageable);
        return points;
    }

    private Page<Point> findPointByCustEmail(String keyword, Pageable pageable) {
        Customer customer = customerRepository.findByCustEmail(keyword);
        Page<Point> points = pointRepository.findAllByCustomer_CustEmail(customer.getCustEmail(),pageable);
        return points;
    }

    private Page<Point> findPointByCustName(String keyword, Pageable pageable) {
        Customer customer = customerRepository.findByCustName(keyword);
        Page<Point> points = pointRepository.findAllByCustomer_CustName(customer.getCustName(),pageable);
        return points;
    }

    private Page<Point> findPointByUid(String keyword,Pageable pageable) {
        Customer customer = customerRepository.findByMember_MemUid(keyword);
        Page<Point> points = pointRepository.findAllByCustomer_Member_IdOrderByIdDesc(customer.getMember().getId(),pageable);
        return points;
    }
}
