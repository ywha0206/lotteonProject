package com.lotteon.repository.member;

import com.lotteon.entity.member.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findBySellCompanyContaining(String keyword);
}
