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
    private List<Categories> categories;
    private String productName;
    private String productDescription;
    private String summary;

}
