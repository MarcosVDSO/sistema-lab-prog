package com.labprog.labprog.model.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labprog.labprog.model.entities.ProductSkus;

public interface ProductSkusRepository extends JpaRepository<ProductSkus, UUID>{
    
}
