package com.labprog.labprog.model.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labprog.labprog.model.entities.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems, UUID>{
}
