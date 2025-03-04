package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.ProductSkus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private UUID cartItemId;
    private ProductSkus productSku;
    private Long quantity;
}
