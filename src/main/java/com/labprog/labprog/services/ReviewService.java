package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Review;
import com.labprog.labprog.model.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review create(Review review) {
        validateReview(review);

        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public Review addLike(UUID reviewId) {

        Review review = reviewRepository.getReferenceById(reviewId);

        review.setLikes(review.getLikes() + 1);

        return reviewRepository.save(review);
    }

    private void validateReview(Review review) {

        if (review.getComment() == null) {
            throw new RuntimeException("Comment is null");
        }

        if (review.getStars() == null || review.getStars() <= 0) {
            throw new RuntimeException("Stars value is not valid");
        }

    }

}
