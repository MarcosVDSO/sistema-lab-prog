package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import netscape.javascript.JSObject;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuDTO {

    private Products product;
    private CartItems cartItem;
    private Long stockQuantity;
    private Long price;
    private String sku;
    private Map<String, Object> product_attributes;

}
