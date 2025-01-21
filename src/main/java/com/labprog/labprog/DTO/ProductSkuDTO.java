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

    private List<CartItems> cartItem;
    private Long quantity;
    private Double price;
    private String sku;
}
