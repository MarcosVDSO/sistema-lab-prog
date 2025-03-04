package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuDTO {

    private CartItems cartItem;
    private Long stockQuantity;
    private Long price;
    private String sku;
}
