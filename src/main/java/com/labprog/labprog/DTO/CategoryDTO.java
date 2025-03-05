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
public class CategoryDTO {

    private UUID category_id;
    private String category_name;
    private String category_description;

    @Data
    @Builder
    @AllArgsConstructor
    public static class OrderItemsDTO {
        private UUID orderItemId;
        private Long quantity;
        private Long price;
        private Order order;
        private ProductSkus productSkus;
    }
}
