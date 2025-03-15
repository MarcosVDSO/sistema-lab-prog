package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Review;
import com.labprog.labprog.model.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review create(Review review) {
        validateReview(review);
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
