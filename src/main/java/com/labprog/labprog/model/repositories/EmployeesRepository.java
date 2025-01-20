package com.labprog.labprog.model.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labprog.labprog.model.entities.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, UUID>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
