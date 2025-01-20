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
public class SubCategoryTest {
    @Autowired
    private SubCategoryService subCategoriesService;

    @Test
    public void shouldCreateAnSubCategorySuccessfully() {

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
    public void shouldntCreateAnSubCategoryIfDescriptionNotExists() {

    }

    @Test
    public void shouldntCreateAnSubCategoryIfNameNotExists() {

    }

    @Test
    public void shouldntCreateAnSubCategoryIfProductExists() {

    }

    @Test
    public void shouldReturnOneSubCategorySuccessfully() {

    }

    @Test
    public void shouldReturnNothingIfSubCategoryDoesNotExist() {

    }

    @Test
    public void shouldUpdateSubCategorySuccessfully() {

    }

    @Test
    public void shouldntUpdateSubCategoryIfSubCategoryNotExists() {

    }

    @Test
    public void shouldDeleteSubCategorySuccessfully() {

    }

    @Test
    public void shouldntDeleteSubCategoryIfSubCategoryDoesNotExist() {

    }

}
