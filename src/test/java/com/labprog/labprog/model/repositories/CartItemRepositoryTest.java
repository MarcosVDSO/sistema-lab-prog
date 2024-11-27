package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.CartItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CartItemRepositoryTest {
    @Autowired
    CartItemsRepository repository;

    @Test
    public void testCreateCartItem() {

        CartItems cartItem = CartItems.builder()
                .quantity(10L)
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        CartItems save = repository.save(cartItem);

        Assertions.assertNotNull(save);
        Assertions.assertEquals(cartItem.getQuantity(), save.getQuantity());
        Assertions.assertEquals(cartItem.getCreatedAt(), save.getCreatedAt());
        Assertions.assertEquals(cartItem.getUpdatedAt(), save.getUpdatedAt());
    }

    @Test
    public void testFindOneCartItem() {

    }

    @Test
    public void testFindAllCartItems() {

    }

    @Test
    public void testUpdateCartItem() {

    }

    @Test
    public void testDeleteCartItem() {

    }

    @Test
    public void testDeleteManyCartItems() {

    }
}
