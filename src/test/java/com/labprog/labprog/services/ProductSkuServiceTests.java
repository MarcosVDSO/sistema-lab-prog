package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.repositories.ProductSkusRepository;
import com.labprog.labprog.model.repositories.ProductsRepository;
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
public class ProductSkuServiceTests {
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductSkusRepository productSkusRepository;

    @Test
    public void shouldCreateAnProductSkuSuccessfully() {

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        Assertions.assertEquals(10L, productSku.getStockQuantity());
        Assertions.assertEquals(10200, productSku.getPrice());
        Assertions.assertEquals("123", productSku.getSku());
        Assertions.assertEquals("path", productSku.getProductImage());

    }

    @Test
    public void shouldntCreateAnProductSkuIfQuantityNotExists() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.createProductSku(ProductSkus.builder()
                    .price(10200L)
                    .sku("123")
                    .productImage("path")
                    .build()
            );
        });

        Assertions.assertEquals("Stock quantity value is not valid", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnProductSkuIfQuantityIsLessThanZero() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.createProductSku(ProductSkus.builder()
                    .stockQuantity(-1L)
                    .price(10200L)
                    .sku("123")
                    .productImage("path")
                    .build()
            );
        });

        Assertions.assertEquals("Stock quantity value is not valid", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnProductSkuIfPriceNotExists() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.createProductSku(ProductSkus.builder()
                    .stockQuantity(10L)
                    .sku("123")
                    .productImage("path")
                    .build()
            );
        });

        Assertions.assertEquals("Price value is not valid", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnProductSkuIfPriceIsLessThanZero() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.createProductSku(ProductSkus.builder()
                    .stockQuantity(10L)
                    .price(-10L)
                    .sku("123")
                    .productImage("path")
                    .build()
            );
        });

        Assertions.assertEquals("Price value is not valid", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnProductSkuIfSkutExists() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.createProductSku(ProductSkus.builder()
                    .stockQuantity(10L)
                    .price(10200L)
                    .productImage("path")
                    .build()
            );
        });

        Assertions.assertEquals("Sku value is not valid", exception.getMessage());
    }

    @Test
    public void shouldReturnOneProductSkuSuccessfully() {
        ProductSkus productSkus = ProductSkus.builder()
                .sku("123456")
                .stockQuantity(10L)
                .price(40000L)
                .productImage("path")
                .build();

        ProductSkus save = productSkusRepository.save(productSkus);

        ProductSkus foundProduct = productSkuService.getProductSkuById(save.getProductSkuId());

        Assertions.assertEquals(10L, foundProduct.getStockQuantity());
        Assertions.assertEquals(40000L, foundProduct.getPrice());
        Assertions.assertEquals("123456", foundProduct.getSku());
    }

    @Test
    public void shouldReturnNothingIfProductSkuDoesNotExist() {
        UUID productSkuId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.getProductSkuById(productSkuId);
        });

        Assertions.assertEquals("Product sku not found with id" + productSkuId, exception.getMessage());
    }

    @Test
    public void shouldUpdateProductSkuSuccessfully() {
        ProductSkus productSkus = ProductSkus.builder()
                .sku("123456")
                .stockQuantity(10L)
                .price(40000L)
                .productImage("path")
                .build();

        ProductSkus save = productSkusRepository.save(productSkus);

        ProductSkus newProductSkuData = ProductSkus.builder()
                .sku("123456")
                .build();

        ProductSkus updatedProductSkuProduct = productSkuService.updateProductSku(save.getProductSkuId(), newProductSkuData);

        Assertions.assertEquals("123456", updatedProductSkuProduct.getSku());
    }

    @Test
    public void shouldntUpdateProductSkuIfProductSkuNotExists() {
        UUID productSkuId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.updateProductSku(productSkuId, new ProductSkus());
        });

        Assertions.assertEquals("Product sku not found with id" + productSkuId, exception.getMessage());
    }

    @Test
    public void shouldDeleteProductSkuSuccessfully() {
        ProductSkus productSkus = ProductSkus.builder()
                .sku("123456")
                .stockQuantity(10L)
                .price(40000L)
                .productImage("path")
                .build();

        ProductSkus save = productSkusRepository.save(productSkus);

        ProductSkus deletedProduct = productSkuService.deleteProductSkuById(save.getProductSkuId());

        Assertions.assertEquals(10L, deletedProduct.getStockQuantity());
        Assertions.assertEquals(40000L, deletedProduct.getPrice());
        Assertions.assertEquals("123456", deletedProduct.getSku());
    }

    @Test
    public void shouldntDeleteProductSkuIfProductSkuDoesNotExist() {
        UUID productSkuId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSkuService.deleteProductSkuById(productSkuId);
        });

        Assertions.assertEquals("Product sku not found with id" + productSkuId, exception.getMessage());
    }

}
