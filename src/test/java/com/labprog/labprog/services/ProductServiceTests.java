package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProductServiceTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @Test
    public void shouldCreateAnProductSuccessfully() {

        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        Assertions.assertEquals("product name", createdProduct.getProductName());
        Assertions.assertEquals("description", createdProduct.getProductDescription());
        Assertions.assertEquals("manufacturer", createdProduct.getManufacturer());
        Assertions.assertEquals("brand name", createdProduct.getBrandName());
        Assertions.assertEquals("summary", createdProduct.getSummary());

    }

    @Test
    public void shouldAddAnProductSku() {
        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        ProductSkus productSku = ProductSkus.builder()
                .sku("123")
                .price(10L)
                .stockQuantity(10L)
                .productImage("path")
                .build();

        productService.addProductSku(createdProduct.getProductId(), productSku);

        Assertions.assertTrue(createdProduct.getProductSkus().contains(productSku));

    }


    @Test
    public void shouldAddANewCategories() {
        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        Categories category = Categories.builder()
                .categoryName("nome")
                .categoryDescription("description")
                .build();

        productService.addNewCategory(createdProduct.getProductId(), category);

        Assertions.assertEquals(createdProduct.getCategory().getCategoryId(), category.getCategoryId());
    }


    @Test
    public void shouldAddACategories() {
        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        Categories categoryData = Categories.builder()
                .categoryName("nome")
                .categoryDescription("description")
                .build();

        Categories category = categoryService.createCategory(categoryData);

        productService.addCategory(createdProduct.getProductId(), category.getCategoryId());

        Assertions.assertEquals(product.getCategory().getCategoryId(), category.getCategoryId());

    }

    @Test
    public void shouldAddAComment() {
        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        Users user = Users.builder()
                .username("marcos.estrela")
                .firstname("marcos")
                .lastname("estrela")
                .email("exemplo@email.com")
                .cpf("00000000000")
                .password("12345678")
                .role("ADMIN")
                .status("ENABLED")
                .build();

        Users createdUser = userService.save(user);

        Review review = Review.builder()
                .comment("comment")
                .stars(2)
                .title("title")
                .likes(0)
                .build();

        productService.addComment(product.getProductId(), user.getUserId(), review);

        Assertions.assertTrue(product.getReviews().contains(review));
        Assertions.assertTrue(user.getReviews().contains(review));
    }

    @Test
    public void shouldntCreateAProductIfProductNameIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productName("   ")
                    .productDescription("description")
                    .manufacturer("manufacturer")
                    .brandName("brand name")
                    .summary("summary")
                    .build()
            );
        });

        Assertions.assertEquals("Product name cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAProductIfDescriptionIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productName("product name")
                    .productDescription("   ")
                    .manufacturer("manufacturer")
                    .brandName("brand name")
                    .summary("summary")
                    .build()
            );
        });

        Assertions.assertEquals("Product description cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAProductIfManufacturerIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productName("product name")
                    .productDescription("description")
                    .manufacturer("   ")
                    .brandName("brand name")
                    .summary("summary")
                    .build()
            );
        });

        Assertions.assertEquals("Manufacturer cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAProductIfBrandNameIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productName("product name")
                    .productDescription("description")
                    .manufacturer("manufacturer")
                    .brandName("   ")
                    .summary("summary")
                    .build()
            );
        });

        Assertions.assertEquals("Brand name cannot be null or empty", exception.getMessage());
    }


    @Test
    public void shouldntCreateAProductIfSummaryIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.createProduct(Products.builder()
                    .productName("product name")
                    .productDescription("description")
                    .manufacturer("manufacturer")
                    .brandName("brand name")
                    .summary("   ")
                    .build()
            );
        });

        Assertions.assertEquals("Summary cannot be null or empty", exception.getMessage());
    }


    @Test
    public void shouldReturnOneProduct() {
        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        Products foundedProduct = productService.getProductById(product.getProductId());

        Assertions.assertEquals(createdProduct.getProductId(), foundedProduct.getProductId());
    }

    @Test
    public void shouldReturnNothingIfProductNotExists() {
        UUID productId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.getProductById(productId);
        });

        Assertions.assertEquals("Product not found!", exception.getMessage());
    }

    @Test
    public void shouldUpdateProduct() {
        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        Products productData = Products.builder()
                .productName("new name")
                .build();

        Products updatedProduct = productService.updateProduct(createdProduct.getProductId(), productData);

        Assertions.assertEquals("new name", updatedProduct.getProductName());

    }

    @Test
    public void shouldntUpdateIfProductNotExists() {
        UUID productId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(productId, Products.builder().build());
        });

        Assertions.assertEquals("Product not found!", exception.getMessage());
    }

    @Test
    public void shouldDeleteProduct() {
        Products product = Products.builder()
                .productName("product name")
                .productDescription("description")
                .manufacturer("manufacturer")
                .brandName("brand name")
                .summary("summary")
                .build();

        Products createdProduct = productService.createProduct(product);

        productService.deleteProduct(createdProduct.getProductId());
    }

    @Test
    public void shouldntDeleteProductNotExists() {
        UUID productId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct(productId);
        });

        Assertions.assertEquals("Product not found!", exception.getMessage());
    }

}
