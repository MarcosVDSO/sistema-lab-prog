//package com.labprog.labprog.services;
//
//import com.labprog.labprog.model.entities.CartItems;
//import com.labprog.labprog.model.entities.Categories;
//import com.labprog.labprog.model.entities.ProductSkus;
//import com.labprog.labprog.model.entities.Products;
//import com.labprog.labprog.model.repositories.ProductSkusRepository;
//import com.labprog.labprog.model.repositories.ProductsRepository;
//import org.checkerframework.checker.units.qual.A;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.UUID;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ActiveProfiles("test")
//public class ProductSkuServiceTest {
//    @Autowired
//    private ProductSkuService productSkuService;
//    @Autowired
//    private ProductsRepository productsRepository;
//    @Autowired
//    private ProductSkusRepository productSkusRepository;
//
//    @Test
//    public void shouldCreateAnProductSkuSuccessfully() {
//
//        ProductSkus productSkus = ProductSkus.builder()
//                .quantity(10L)
//                .price(10.2)
//                .sku("123")
//                .build();
//
//        ProductSkus productSku = productSkuService.createProductSku(productSkus);
//
//        Assertions.assertEquals(10L, productSku.getQuantity());
//        Assertions.assertEquals(10.2, productSku.getPrice());
//        Assertions.assertEquals("123", productSku.getSku());
//
//    }
//
//    @Test
//    public void shouldntCreateAnProductSkuIfQuantityNotExists() {
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
//            productSkuService.createProductSku(ProductSkus.builder()
//                    .price(10.2)
//                    .sku("123")
//                    .build()
//            );
//        });
//
//        Assertions.assertEquals("Quantity value is not valid", exception.getMessage());
//    }
//
//    @Test
//    public void shouldntCreateAnProductSkuIfPriceNotExists() {
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
//            productSkuService.createProductSku(ProductSkus.builder()
//                    .quantity(10L)
//                    .sku("123")
//                    .build()
//            );
//        });
//
//        Assertions.assertEquals("Price value is not valid", exception.getMessage());
//    }
//
//    @Test
//    public void shouldntCreateAnProductSkuIfSkutExists() {
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
//            productSkuService.createProductSku(ProductSkus.builder()
//                    .quantity(10L)
//                    .price(10.2)
//                    .build()
//            );
//        });
//
//        Assertions.assertEquals("Sku value is not valid", exception.getMessage());
//    }
//
//    @Test
//    public void shouldReturnOneProductSkuSuccessfully() {
//        ProductSkus productSkus = ProductSkus.builder()
//                .cartItem(new ArrayList<CartItems>())
//                .sku("123456")
//                .quantity(10L)
//                .price(5.0D)
//                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .build();
//
//        ProductSkus save = productSkusRepository.save(productSkus);
//
//        ProductSkus foundProduct = productSkuService.getProductSkuById(save.getProductSkuId());
//
//        Assertions.assertEquals(10L, foundProduct.getQuantity());
//        Assertions.assertEquals(5.0D, foundProduct.getPrice());
//        Assertions.assertEquals("123456", foundProduct.getSku());
//    }
//
//    @Test
//    public void shouldReturnNothingIfProductSkuDoesNotExist() {
//        UUID productSkuId = UUID.randomUUID();
//
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
//            productSkuService.getProductSkuById(productSkuId);
//        });
//
//        Assertions.assertEquals("Product sku not found with id" + productSkuId, exception.getMessage());
//    }
//
//    @Test
//    public void shouldUpdateProductSkuSuccessfully() {
//        ProductSkus productSkus = ProductSkus.builder()
//                .cartItem(new ArrayList<CartItems>())
//                .sku("123456")
//                .quantity(10L)
//                .price(5.0D)
//                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .build();
//
//        ProductSkus save = productSkusRepository.save(productSkus);
//
//        ProductSkus newProductSkuData = ProductSkus.builder()
//                .cartItem(new ArrayList<CartItems>())
//                .sku("123456")
//                .quantity(12L)
//                .price(5.0D)
//                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .build();
//
//        ProductSkus updatedProductSkuProduct = productSkuService.updateProductSku(save.getProductSkuId(), newProductSkuData);
//
//        Assertions.assertEquals(12L, updatedProductSkuProduct.getQuantity());
//        Assertions.assertEquals(5.0D, updatedProductSkuProduct.getPrice());
//        Assertions.assertEquals("123456", updatedProductSkuProduct.getSku());
//    }
//
//    @Test
//    public void shouldntUpdateProductSkuIfProductSkuNotExists() {
//        UUID productSkuId = UUID.randomUUID();
//
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
//            productSkuService.updateProductSku(productSkuId, new ProductSkus());
//        });
//
//        Assertions.assertEquals("Product sku not found with id" + productSkuId, exception.getMessage());
//    }
//
//    @Test
//    public void shouldDeleteProductSkuSuccessfully() {
//        ProductSkus productSkus = ProductSkus.builder()
//                .cartItem(new ArrayList<CartItems>())
//                .sku("123456")
//                .quantity(10L)
//                .price(5.0D)
//                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
//                .build();
//
//        ProductSkus save = productSkusRepository.save(productSkus);
//
//        ProductSkus deletedProduct = productSkuService.deleteProductSkuById(save.getProductSkuId());
//
//        Assertions.assertEquals(10L, deletedProduct.getQuantity());
//        Assertions.assertEquals(5.0D, deletedProduct.getPrice());
//        Assertions.assertEquals("123456", deletedProduct.getSku());
//    }
//
//    @Test
//    public void shouldntDeleteProductSkuIfProductSkuDoesNotExist() {
//        UUID productSkuId = UUID.randomUUID();
//
//        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
//            productSkuService.deleteProductSkuById(productSkuId);
//        });
//
//        Assertions.assertEquals("Product sku not found with id" + productSkuId, exception.getMessage());
//    }
//
//}
