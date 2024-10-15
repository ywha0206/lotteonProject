package com.lotteon.repository.category;

import com.lotteon.entity.category.CategoryArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryArticleRepository extends JpaRepository<CategoryArticle, Long> {
}
