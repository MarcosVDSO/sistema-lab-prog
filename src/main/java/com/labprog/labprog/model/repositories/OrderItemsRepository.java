package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemsRepository extends JpaRepository<OrderItems, UUID> {
}
