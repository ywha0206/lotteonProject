package com.lotteon.repository.article;

import com.lotteon.entity.article.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq,Long> {
}
