package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    ProductsRepository repository;

    @Test
    public void testCreateProduct() {
        Products product = Products.builder()
                .productSkus(new ArrayList<ProductSkus>())
                .categories(new ArrayList<Categories>())
                .productName("produto")
                .productDescription("descricao")
                .summary("Sumario")
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        Products save = repository.save(product);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(product.getProductSkus(), save.getProductSkus());
        Assertions.assertEquals(product.getCategories(), save.getCategories());
        Assertions.assertEquals(product.getProductName(), save.getProductName());
        Assertions.assertEquals(product.getProductDescription(), save.getProductDescription());
        Assertions.assertEquals(product.getSummary(), save.getSummary());
        Assertions.assertEquals(product.getCreatedAt(), save.getCreatedAt());
        Assertions.assertEquals(product.getUpdatedAt(), save.getUpdatedAt());
    }
}
