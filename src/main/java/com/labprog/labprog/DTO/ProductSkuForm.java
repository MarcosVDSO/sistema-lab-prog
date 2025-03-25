package com.labprog.labprog.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuForm {
    Long price;
    String sku;
    Long stockQuantity;
    MultipartFile image;
}
