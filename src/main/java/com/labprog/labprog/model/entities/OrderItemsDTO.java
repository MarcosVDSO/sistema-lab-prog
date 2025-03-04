package com.labprog.labprog.model.entities;

import com.labprog.labprog.DTO.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderItemsDTO {
    private UUID orderItemId;
    private Long quantity;
    private Long price;
    private Order order;
    private ProductSkus productSkus;
}
