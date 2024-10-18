package com.lotteon.repository.article;

import com.lotteon.entity.article.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<Qna,Long> {
    List<Qna> findAllByOrderByQnaRdateDesc(); // 최신 등록순으로 문의 내역 조회

}
