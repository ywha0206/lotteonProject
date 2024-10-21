package com.lotteon.repository.point;

import com.lotteon.entity.member.Member;
import com.lotteon.entity.point.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Page<Coupon> findAllByOrderByIdDesc(Pageable pageable);

    Page<Coupon> findAllByMemberOrderByIdDesc(Member member, Pageable pageable);

    Page<Coupon> findAllByCouponNameContainingOrderByIdDesc(String keyword, Pageable pageable);

    Page<Coupon> findAllByIdOrderByIdDesc(long l, Pageable pageable);

    Page<Coupon> findAllByIdAndMemberOrderByIdDesc(long l,Member member, Pageable pageable);

    Page<Coupon> findAllByCouponNameAndMemberOrderByIdDesc(String keyword, Member member, Pageable pageable);
}
