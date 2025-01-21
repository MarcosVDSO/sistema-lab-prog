package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
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
public class ProductSkusRepositoryTest {
    @Autowired
    ProductSkusRepository repository;
    @Autowired
    ProductsRepository repositoryProducts;

    @Test
    public void testCreateProductSkus() {
        Products product = Products.builder()
                .productSkus(new ArrayList<ProductSkus>())
                .categories(new ArrayList<Categories>())
                .productName("produto2")
                .productDescription("descricao2")
                .summary("Sumario2")
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        Products saveProduct = repositoryProducts.save(product);
        ProductSkus productSkus = ProductSkus.builder()
                .cartItem(new ArrayList<CartItems>())
                .product(saveProduct)
                .sku("123456")
                .quantity(10L)
                .price(5.0D)
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        ProductSkus save = repository.save(productSkus);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(productSkus.getCartItem(), save.getCartItem());
        Assertions.assertEquals(productSkus.getProduct(), save.getProduct());
        Assertions.assertEquals(productSkus.getSku(), save.getSku());
        Assertions.assertEquals(productSkus.getQuantity(), save.getQuantity());
        Assertions.assertEquals(productSkus.getPrice(), save.getPrice());
        Assertions.assertEquals(productSkus.getCreatedAt(), save.getCreatedAt());
        Assertions.assertEquals(productSkus.getUpdatedAt(), save.getUpdatedAt());
    }
}
