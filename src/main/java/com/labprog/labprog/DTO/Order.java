package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Customers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Order {
    private UUID orderId;
    private Customers customer;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
