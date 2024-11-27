package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.Carts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CartsRepositoyTest {
    @Autowired
    CartsRepository repository;

    @Test
    public void testCreateCart() {
        Carts cart = Carts.builder()
                .cartItems(new ArrayList<CartItems>())
                .total(10L)
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        Carts save = repository.save(cart);

        Assertions.assertNotNull(save);
        Assertions.assertEquals(cart.getCartItems(), save.getCartItems());
        Assertions.assertEquals(cart.getTotal(), save.getTotal());
        Assertions.assertEquals(cart.getCreatedAt(), save.getCreatedAt());
        Assertions.assertEquals(cart.getUpdatedAt(), save.getUpdatedAt());
    }

    @Test
    public void testFindOneCart() {

    }

    @Test
    public void testFindAllCarts() {

    }

    @Test
    public void testUpdateCart() {

    }

    @Test
    public void testDeleteCart() {

    }

    @Test
    public void testDeleteManyCarts() {

    }
}
