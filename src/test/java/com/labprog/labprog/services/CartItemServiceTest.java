package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.repositories.CartsRepository;
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
public class CartItemServiceTest {
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductSkuService productSkuService;
    @Autowired
    CartsRepository cartsRepository;

    @Test
    public void shouldCreateACartItemSuccessfully() {
        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        cartItemService.createCartItem(productSku, 10L);
    }

    @Test
    public void shouldntCreateAnCartItemIfQuantityIsNegative() {
        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartItemService.createCartItem(productSku, -10L);
        });

        Assertions.assertEquals("Cart item quantity cannot be 0 or less", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnCartItemIfQuantityIsGreaterThanStock() {
        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartItemService.createCartItem(productSku, 11L);
        });

        Assertions.assertEquals("Cart item quantity is greater than stock", exception.getMessage());
    }

    @Test
    public void shouldUpdateCartItemQuantity() {
        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        Carts cart = cartService.createCart();
        CartItems cartItem = cartItemService.createCartItem(productSku, 10L);
        cart.getCartItems().add(cartItem);
        cartItem.setCart(cart);
        cartsRepository.save(cart);

        cartItem = cartItemService.updateQuantityOfCartItem(cartItem.getCartItemId(), 9L);

        Assertions.assertEquals(9L, cartItem.getQuantity());
    }

    @Test
    public void shouldntUpdateCartItemQuantityIfQuantityGreaterThanStock() {
        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        CartItems cartItem = cartItemService.createCartItem(productSku, 10L);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartItemService.updateQuantityOfCartItem(cartItem.getCartItemId(), 11L);
        });

        Assertions.assertEquals("Quantity cannot be greater than stock", exception.getMessage());
    }

    @Test
    public void shouldUpdateCartItemQuantityIfQuantityLessThanZero() {
        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        CartItems cartItem = cartItemService.createCartItem(productSku, 10L);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            cartItemService.updateQuantityOfCartItem(cartItem.getCartItemId(), -11L);
        });

        Assertions.assertEquals("Cart item quantity cannot be 0 or less", exception.getMessage());
    }

    @Test
    public void shouldDeleteCartItem() {
        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        ProductSkus productSku = productSkuService.createProductSku(productSkus);

        CartItems cartItem = cartItemService.createCartItem(productSku, 10L);

        cartItemService.deleteCartItem(cartItem);
    }

}
