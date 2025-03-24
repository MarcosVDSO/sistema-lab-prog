package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.ProductSkus;
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
public class CartServiceTest {
    @Autowired
    CartService cartService;
    @Autowired
    ProductSkuService productSkuService;

    @Test
    public void shouldCreateACartSuccessfully() {

        Carts carts = cartService.createCart();

    }

    @Test
    public void shouldAddProductToCart() {

        Carts carts = cartService.createCart();

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        carts = cartService.addProductToCart(carts.getCartId(), productSku.getProductSkuId(), 10L);

        Assertions.assertFalse(carts.getCartItems().isEmpty());
    }

    @Test
    public void shouldntAddProductToCartIfQuantityIsLowerThanZero() {
        Carts carts = cartService.createCart();

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartService.addProductToCart(carts.getCartId(), productSku.getProductSkuId(), -10L);
        });

        Assertions.assertEquals("Cart item quantity cannot be 0 or less", exception.getMessage());
    }

    @Test
    public void shouldntAddProductToCartIfQuantityIsGreaterThanStock() {
        Carts carts = cartService.createCart();

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);


        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartService.addProductToCart(carts.getCartId(), productSku.getProductSkuId(), 11L);
        });

        Assertions.assertEquals("Cart item quantity is greater than stock", exception.getMessage());
    }

    @Test
    public void shouldRemoveProductToCart() {

        Carts carts = cartService.createCart();

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);
        cartService.addProductToCart(carts.getCartId(), productSku.getProductSkuId(), 10L);

        carts = cartService.removeProductFromCart(carts.getCartId(), carts.getCartItems().get(0).getCartItemId());

        Assertions.assertTrue(carts.getCartItems().isEmpty());
    }

    @Test
    public void shouldntRemoveProductToCartIfProductNotExists() {

        Carts carts = cartService.createCart();

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);
        cartService.addProductToCart(carts.getCartId(), productSku.getProductSkuId(), 10L);

        UUID cartItemId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartService.removeProductFromCart(carts.getCartId(), cartItemId);
        });

        Assertions.assertEquals("Cart item not found", exception.getMessage());
    }

    @Test
    public void shouldClearCart() {

        Carts carts = cartService.createCart();

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        carts = cartService.clearCart(carts.getCartId());

        Assertions.assertEquals(0L, carts.getTotal());
        Assertions.assertTrue(carts.getCartItems().isEmpty());
    }

    @Test
    public void shouldntClearCartIfCartNotExists() {

        UUID cartId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartService.clearCart(cartId);
        });

        Assertions.assertEquals("Cart not found!", exception.getMessage());
    }

}
