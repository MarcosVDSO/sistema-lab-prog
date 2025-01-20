package com.labprog.labprog.model.repositories;

import java.util.Optional;
import java.util.UUID;

import com.labprog.labprog.model.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsRepository extends JpaRepository<Carts, UUID>{

    Optional<Carts> findByCustomer_CustomerId(UUID customerId);
}
