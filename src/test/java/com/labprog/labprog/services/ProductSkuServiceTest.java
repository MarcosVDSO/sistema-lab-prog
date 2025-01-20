package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductSkuServiceTest {
    @Autowired
    private ProductSkuService productSkuService;

    @Test
    public void shouldCreateAnProductSkuSuccessfully() {

        Products product = new Products();

        ProductSkus productSkus = ProductSkus.builder()
                .quantity(10L)
                .price(10.2)
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
