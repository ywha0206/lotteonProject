package com.lotteon.repository.point;

import com.lotteon.entity.member.Customer;
import com.lotteon.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByCustId(Long id);
}
