package com.lotteon.repository.product;


import com.lotteon.entity.member.UserLog;
import com.lotteon.entity.product.ReviewDocu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDocuRepository extends MongoRepository<ReviewDocu,String> {
    boolean existsByCustIdAndProdId(Long userId, Long productId);

    List<ReviewDocu> findByProdId(Long prodId);
}
