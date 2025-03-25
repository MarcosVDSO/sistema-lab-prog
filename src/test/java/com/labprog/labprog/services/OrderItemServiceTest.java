package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.OrderItems;
import com.labprog.labprog.model.entities.Orders;
import com.labprog.labprog.model.entities.ProductSkus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class OrderItemServiceTest {

    @Autowired
    private OrderItemService orderItemService;

    @Test
    public void shouldCreateAnOrderItemSuccessfully() {

        Orders order = Orders.builder()
                .status("DONE")
                .orderItems(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        ProductSkus productSkus = ProductSkus.builder()
                .stockQuantity(10L)
                .price(10200L)
                .sku("123")
                .productImage("path")
                .build();

        OrderItems orderItem = OrderItems.builder()
                .order(order)
                .quantity(10L)
                .productSku(productSkus)
                .build();

        OrderItems createdOrderItem = orderItemService.createOrderItem(orderItem);

        Assertions.assertEquals(10L, orderItem.getQuantity());
        Assertions.assertEquals("123", orderItem.getProductSku().getSku());
        Assertions.assertEquals(0L, orderItem.getProductSku().getStockQuantity());
    }

}
