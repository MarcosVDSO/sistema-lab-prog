package com.labprog.labprog.model.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.labprog.labprog.model.entities.ProductSkus;

public interface ProductSkusRepository extends JpaRepository<ProductSkus, UUID>{
    Page<ProductSkus> findByProduct_ProductId(UUID productId, Pageable pageable);
}
