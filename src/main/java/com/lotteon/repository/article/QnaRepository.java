package com.lotteon.repository.article;

import com.lotteon.entity.article.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<Qna,Long> {
}
