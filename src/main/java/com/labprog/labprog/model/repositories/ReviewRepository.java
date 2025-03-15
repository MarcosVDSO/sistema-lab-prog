package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
