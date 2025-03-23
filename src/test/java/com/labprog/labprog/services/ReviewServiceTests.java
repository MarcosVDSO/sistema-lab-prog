package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Review;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ReviewServiceTests {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void shouldCreateAnReviewSuccessfully() {
        Review review = Review.builder()
                .title("title")
                .comment("comment")
                .stars(5)
                .build();

        Review createdReview = reviewService.create(review);

        Assertions.assertEquals("title", createdReview.getTitle());
        Assertions.assertEquals("comment", createdReview.getComment());
        Assertions.assertEquals(5, createdReview.getStars());
    }

    @Test
    public void shouldntCreateAnReviewIfTitleIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            reviewService.create(Review.builder()
                    .title("   ")
                    .comment("comment")
                    .stars(5)
                    .build()
            );
        });

        Assertions.assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnReviewIfCommentIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            reviewService.create(Review.builder()
                    .title("title")
                    .comment("   ")
                    .stars(5)
                    .build()
            );
        });

        Assertions.assertEquals("Comment cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnReviewIfStarNumberIsInvalid() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            reviewService.create(Review.builder()
                    .title("title")
                    .comment("comment")
                    .stars(7)
                    .build()
            );
        });

        Assertions.assertEquals("Stars value is not valid", exception.getMessage());
    }

    @Test
    public void shouldAddAnLike() {
        Review review = Review.builder()
                .title("title")
                .comment("comment")
                .stars(5)
                .build();

        Review createdReview = reviewService.create(review);

        reviewService.addLike(review.getReviewId());
        reviewService.addLike(review.getReviewId());

        Assertions.assertEquals(2, createdReview.getLikes());
    }

    @Test
    public void shouldDeleteProductSkuSuccessfully() {
        Review review = Review.builder()
                .title("title")
                .comment("comment")
                .stars(5)
                .build();

        Review createdReview = reviewService.create(review);

        reviewService.delete(createdReview.getReviewId());
    }

    @Test
    public void shouldntDeleteProductSkuIfProductSkuDoesNotExist() {
        UUID reviewId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            reviewService.delete(reviewId);
        });

        Assertions.assertEquals("Review not found with id: " + reviewId, exception.getMessage());
    }

}
