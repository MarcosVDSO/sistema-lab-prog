package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.model.repositories.ProductsRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductSkuServiceTest {
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void shouldCreateAnProductSkuSuccessfully() {

        UUID productId = UUID.randomUUID();

        Products product = Products.builder()
                .productId(productId)
                .productSkus(new ArrayList<ProductSkus>())
                .categories(new ArrayList<Categories>())
                .productName("produto")
                .productDescription("descricao")
                .summary("Sumario")
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        ProductSkus productSkus = ProductSkus.builder()
                .productSkuId(UUID.randomUUID())
                .quantity(10L)
                .price(10.2)
                .product(product)
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        Assertions.assertEquals(10L, productSku.getQuantity());
        Assertions.assertEquals(10.2, productSku.getPrice());
        Assertions.assertEquals(product, productSku.getProduct());

    }

    @Test
    public void shouldntCreateAnProductSkuIfProductNotExists() {

    }

    @Test
    public void shouldntCreateAnProductSkuIfPriceNotExists() {

    }

    @Test
    public void shouldntCreateAnProductSkuIfProductExists() {

    }

    @Test
    public void shouldReturnOneProductSkuSuccessfully() {

    }

    @Test
    public void shouldReturnNothingIfProductSkuDoesNotExist() {

    }

    @Test
    public void shouldUpdateProductSkuSuccessfully() {

    }

    @Test
    public void shouldntUpdateProductSkuIfProductSkuNotExists() {

    }

    @Test
    public void shouldDeleteProductSkuSuccessfully() {

    }

    @Test
    public void shouldntDeleteProductSkuIfProductSkuDoesNotExist() {

    }

}
