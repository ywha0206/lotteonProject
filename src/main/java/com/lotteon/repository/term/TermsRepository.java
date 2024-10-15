package com.lotteon.repository.term;

import com.lotteon.entity.term.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<Terms,Long> {
}
