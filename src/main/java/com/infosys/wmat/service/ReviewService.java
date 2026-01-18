package com.infosys.wmat.service;

import com.infosys.wmat.dto.ReviewRequest;
import com.infosys.wmat.entity.Product;
import com.infosys.wmat.entity.Review;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.ProductRepository;
import com.infosys.wmat.repository.ReviewRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Review addReview(Long userId, ReviewRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        return reviewRepository.save(review);
    }

    public List<Review> getProductReviews(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}