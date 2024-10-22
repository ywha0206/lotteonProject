package com.lotteon.service.point;

import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCustCouponDto;
import com.lotteon.dto.responseDto.GetCustomerCouponDto;
import com.lotteon.entity.point.Coupon;
import com.lotteon.entity.point.CustomerCoupon;
import com.lotteon.repository.point.CouponRepository;
import com.lotteon.repository.point.CustomerCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerCouponService {

    private final CustomerCouponRepository customerCouponRepository;
    private final CouponRepository couponRepository;

    public void postCustCoupon(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ) {
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Coupon coupon = couponRepository.findById((long)4).orElseThrow();

        Optional<CustomerCoupon> customerCoupon = customerCouponRepository.findByCustomerAndCoupon(auth.getUser().getCustomer(),coupon);
        if(customerCoupon.isPresent()){
            this.updateCustCouponCnt(customerCoupon);
            return;
        }


        CustomerCoupon newCustomerCoupon = CustomerCoupon.builder()
                .coupon(coupon)
                .couponCnt(1)
                .couponState(1)
                .customer(auth.getUser().getCustomer())
                .build();

        customerCouponRepository.save(newCustomerCoupon);
    }

    public void useCustCoupon(){
        MyUserDetails auth = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Coupon coupon = couponRepository.findById((long)2).orElseThrow();

        Optional<CustomerCoupon> customerCoupon = customerCouponRepository.findByCustomerAndCoupon(auth.getUser().getCustomer(),coupon);

        if(customerCoupon.isEmpty()){
            return;
        }

        customerCoupon.get().updateCustCouponCntMinus();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteCustCoupon(){
        LocalDate today = LocalDate.now();

        List<Coupon> coupons = couponRepository.findAll();

        for(Coupon coupon : coupons){
            LocalDate end = LocalDate.parse(coupon.getCouponExpiration().substring(coupon.getCouponExpiration().indexOf("~")+1).trim());
            if(today.isAfter(end.plusMonths(1))){
                this.deleteExpirationCoupon(coupon);
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void expirateCustCoupon(){
        LocalDate today = LocalDate.now();

        List<Coupon> coupons = couponRepository.findAll();

        for(Coupon coupon : coupons){
            LocalDate start = LocalDate.parse(coupon.getCouponExpiration().substring(0,coupon.getCouponExpiration().indexOf("~")));
            LocalDate end = LocalDate.parse(coupon.getCouponExpiration().substring(coupon.getCouponExpiration().indexOf("~")+1));
            if(today.isBefore(start) && today.isAfter(end)){
                this.updateCustCouponState(coupon);
            }
        }
    }

    public Page<GetCustomerCouponDto> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 5);

        Page<CustomerCoupon> coupons = customerCouponRepository.findAllByOrderByCouponStateAscCouponUDateDesc(pageable);
        Page<GetCustomerCouponDto> dtos = coupons.map(v->v.toGetCustomerCouponDto());

        return dtos;
    }

    private void updateCustCouponState(Coupon coupon) {
        List<CustomerCoupon> coupons = customerCouponRepository.findAllByCoupon(coupon);
        coupons.forEach(CustomerCoupon::updateCustCouponState);
    }


    private void updateCustCouponCnt(Optional<CustomerCoupon> customerCoupon) {
        CustomerCoupon updateCustomerCoupon = customerCoupon.get();
        updateCustomerCoupon.updateCustCouponCnt();
    }

    private void deleteExpirationCoupon(Coupon coupon) {
        List<CustomerCoupon> coupons = customerCouponRepository.findAllByCoupon(coupon);
        customerCouponRepository.deleteAll(coupons);
    }


}
