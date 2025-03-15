package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderDTO {
    private UUID orderId;
    private Users customer;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
