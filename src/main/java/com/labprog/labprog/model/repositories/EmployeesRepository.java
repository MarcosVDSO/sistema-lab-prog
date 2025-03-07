package com.labprog.labprog.model.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labprog.labprog.model.entities.Employees;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmployeesRepository extends JpaRepository<Employees, UUID>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    UserDetails findByUsername(String username);
}
