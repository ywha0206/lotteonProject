package com.lotteon.service.point;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.responseDto.GetPointsDto;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.point.Point;
import com.lotteon.repository.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {
    private final PointRepository pointRepository;

    public Page<GetPointsDto> findAllByCustomer(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Customer customer = auth.getUser().getCustomer();

        Page<Point> points = pointRepository.findAllByCustId(customer.getId(),pageable);
        Page<GetPointsDto> dtos = points.map(v->v.toGetPointsDto());

        return dtos;
    }
}
