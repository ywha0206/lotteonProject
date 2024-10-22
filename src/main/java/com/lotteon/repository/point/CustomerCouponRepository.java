package com.lotteon.repository.point;

import com.lotteon.entity.member.Customer;
import com.lotteon.entity.point.Coupon;
import com.lotteon.entity.point.CustomerCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, Long> {
    Optional<CustomerCoupon> findByCustomerAndCoupon(Customer customer, Coupon coupon);

    List<CustomerCoupon> findAllByCoupon(Coupon coupon);


    Page<CustomerCoupon> findAllByOrderByCouponStateAscCouponUDateDesc(Pageable pageable);
}
