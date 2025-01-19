package com.labprog.labprog.model.repositories;

import java.util.UUID;

import com.labprog.labprog.model.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, UUID>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
