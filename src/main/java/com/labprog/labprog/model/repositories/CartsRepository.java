package com.labprog.labprog.model.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsRepository extends JpaRepository<CartsRepository, UUID>{
    
}
