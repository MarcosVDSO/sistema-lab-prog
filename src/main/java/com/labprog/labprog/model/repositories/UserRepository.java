package com.labprog.labprog.model.repositories;

import java.util.UUID;

import com.labprog.labprog.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, UUID>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    UserDetails findByUsername(String username);
}
