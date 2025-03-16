package com.labprog.labprog.services;


import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.model.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void shouldCreateAnProductSuccessfully() {

        Products product = Products.builder()
                .productName("Fone de ouvido")
                .productDescription("Um fone muito foda")
                .summary("Um fone").build();

        Products createdProduct = productService.createProduct(product);

        Assertions.assertEquals("Fone de ouvido", createdProduct.getProductName());
        Assertions.assertEquals("Um fone muito foda", createdProduct.getProductDescription());
        Assertions.assertEquals("Um fone", createdProduct.getSummary());

    }

    @Test
    public void shouldntCreateAnProductIfSummaryNotExists() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productName("Fone de ouvido")
                    .productDescription("Um fone muito foda")
                    .build()
            );
        });

        Assertions.assertEquals("Product summary is empty", exception.getMessage());

    }

    @Test
    public void shouldntCreateAnProductIfNameNotExists() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productDescription("Um fone muito foda")
                    .summary("Um fone").build()
            );
        });

        Assertions.assertEquals("Product name is required", exception.getMessage());

    }

    @Test
    public void shouldntCreateAnProductIfProductDescriptionExists() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productName("Fone de ouvido")
                    .summary("Um fone").build()
            );
        });

        Assertions.assertEquals("Product description is empty", exception.getMessage());

    }

    @Test
    public void shouldReturnOneProductSuccessfully() {

        Products product = Products.builder()
                .productSkus(new ArrayList<ProductSkus>())
                .productName("produto")
                .productDescription("descricao")
                .summary("Sumario")
                .build();

        Products save = productsRepository.save(product);

        Products foundProduct = productService.getProductById(save.getProductId());

        Assertions.assertEquals("produto", foundProduct.getProductName());
        Assertions.assertEquals("descricao", foundProduct.getProductDescription());
        Assertions.assertEquals("Sumario", foundProduct.getSummary());

    }

    @Test
    public void shouldReturnNothingIfProductDoesNotExist() {

        UUID productId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.getProductById(productId);
        });

        Assertions.assertEquals("Product not found with id" + productId, exception.getMessage());
    }

    @Test
    public void shouldUpdateProductSuccessfully() {
        Products product = Products.builder()
                .productSkus(new ArrayList<ProductSkus>())
                .productName("produto")
                .productDescription("descricao")
                .summary("Sumario")
                .build();

        Products save = productsRepository.save(product);

        Products newProductData = Products.builder()
                .productSkus(new ArrayList<ProductSkus>())
                .productName("novo produto")
                .productDescription("descricao")
                .summary("Sumario")
                .build();

        Products updatedProduct = productService.updateProduct(save.getProductId(), newProductData);

        Assertions.assertEquals("novo produto", updatedProduct.getProductName());
        Assertions.assertEquals("descricao", updatedProduct.getProductDescription());
        Assertions.assertEquals("Sumario", updatedProduct.getSummary());
    }

    @Test
    public void shouldntUpdateProducIfProductNotExists() {
        UUID productId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(productId, new Products());
        });

        Assertions.assertEquals("Product not found with id" + productId, exception.getMessage());
    }

    @Test
    public void shouldDeleteProductSuccessfully() {

        Products product = Products.builder()
                .productSkus(new ArrayList<ProductSkus>())
                .productName("produto")
                .productDescription("descricao")
                .summary("Sumario")
                .build();

        Products save = productsRepository.save(product);

        Products deletedProduct = productService.deleteProduct(save.getProductId());

        Assertions.assertEquals("produto", deletedProduct.getProductName());
        Assertions.assertEquals("descricao", deletedProduct.getProductDescription());
        Assertions.assertEquals("Sumario", deletedProduct.getSummary());
    }

    @Test
    public void shouldntDeleteProductIfProductDoesNotExist() {
        UUID productId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct(productId);
        });

        Assertions.assertEquals("Product not found with id" + productId, exception.getMessage());
    }

}
