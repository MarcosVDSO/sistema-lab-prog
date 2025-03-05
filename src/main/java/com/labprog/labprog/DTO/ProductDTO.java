package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private List<ProductSkus> productSkus;
    private Categories category;
    private String productName;
    private String productDescription;
    private String summary;
    private String manufacturer;
    private String brandName;
    private String productImage;

}
