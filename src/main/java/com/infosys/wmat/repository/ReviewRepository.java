package com.infosys.wmat.repository;

import com.infosys.wmat.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Find all reviews for a specific product
    List<Review> findByProductId(Long productId);
}