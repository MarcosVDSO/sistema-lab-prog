package com.labprog.labprog.services;


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
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void shouldCreateAnProductSuccessfully() {

//        Products product = new Products();
//
//        ProductSkus productSkus = ProductSkus.builder()
//                .quantity(10L)
//                .price(10.2)
//                .product(product).build();
//
//        ProductSkus productSku = productService.createProductSku(productSkus);
//
//        Assertions.assertEquals(10L, productSku.getQuantity());
//        Assertions.assertEquals(10.2, productSku.getPrice());
//        Assertions.assertEquals(product, productSku.getProduct());

    }

    @Test
    public void shouldntCreateAnProductIfProductNotExists() {

    }

    @Test
    public void shouldntCreateAnProductIfNameNotExists() {

    }

    @Test
    public void shouldntCreateAnProductIfProductDescriptionExists() {

    }

    @Test
    public void shouldntCreateAnProductIfProductSummaryExists() {

    }

    @Test
    public void shouldReturnOneProductSuccessfully() {

    }

    @Test
    public void shouldReturnNothingIfProductDoesNotExist() {

    }

    @Test
    public void shouldUpdateProductSuccessfully() {

    }

    @Test
    public void shouldntUpdateProducIfProductNotExists() {

    }

    @Test
    public void shouldDeleteProductSuccessfully() {

    }

    @Test
    public void shouldntDeleteProductIfProductDoesNotExist() {

    }

}
