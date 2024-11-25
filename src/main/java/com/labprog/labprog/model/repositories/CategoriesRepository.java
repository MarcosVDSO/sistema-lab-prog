package com.labprog.labprog.model.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labprog.labprog.model.entities.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, UUID>{
    
}
