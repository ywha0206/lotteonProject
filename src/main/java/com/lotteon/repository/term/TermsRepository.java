package com.lotteon.repository.term;

import com.lotteon.entity.term.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermsRepository extends JpaRepository<Terms,Long> {

    public List<Terms> findAllByTermsType(String termsType);

}
