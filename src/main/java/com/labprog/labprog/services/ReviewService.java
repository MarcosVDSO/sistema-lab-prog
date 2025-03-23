package com.labprog.labprog.services;

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

        review.setLikes(0);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public void addLike(UUID reviewId) {

        Review review = reviewRepository.getReferenceById(reviewId);

        review.setLikes(review.getLikes() + 1);

        reviewRepository.save(review);
    }

    public void delete(UUID reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        reviewRepository.delete(review);

    }

    private void validateReview(Review review) {

        if (review.getTitle() == null || review.getTitle().isBlank()) {
            throw new RuntimeException("Title cannot be null or empty");
        }

        if (review.getComment() == null || review.getComment().isBlank()) {
            throw new RuntimeException("Comment cannot be null or empty");
        }

        if (review.getStars() == null || review.getStars() <= 0 || review.getStars() > 5) {
            throw new RuntimeException("Stars value is not valid");
        }

    }

}
