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
                .productName("produto2")
                .productDescription("descricao2")
                .summary("Sumario2")
                .manufacturer("Fabricante")
                .brandName("Marca")
                .build();

        Products saveProduct = repositoryProducts.save(product);
        ProductSkus productSkus = ProductSkus.builder()
                .product(saveProduct)
                .sku("123456")
                .stockQuantity(10L)
                .price(5L)
                .productImage("teste")
                .build();

        ProductSkus save = repository.save(productSkus);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(productSkus.getCartItem(), save.getCartItem());
        Assertions.assertEquals(productSkus.getProduct(), save.getProduct());
        Assertions.assertEquals(productSkus.getSku(), save.getSku());
        Assertions.assertEquals(productSkus.getStockQuantity(), save.getStockQuantity());
        Assertions.assertEquals(productSkus.getPrice(), save.getPrice());
    }
}
