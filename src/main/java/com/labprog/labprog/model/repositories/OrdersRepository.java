package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    Page<Orders> findByUser_UserId(UUID orderId, Pageable pageable);
}
