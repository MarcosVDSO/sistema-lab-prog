package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.*;
import com.labprog.labprog.model.repositories.CartItemsRepository;
import com.labprog.labprog.model.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserService userService;

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Orders save(UUID userId) {

        Users user = userService.findById(userId);
        Carts cart = user.getCart();

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Orders order = Orders.builder()
                .status("DONE")
                .orderItems(new ArrayList<>())
                .user(user)
                .total(cart.getTotal())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        ordersRepository.save(order);
        for (CartItems cartItem : cart.getCartItems()) {
            OrderItems orderItem = OrderItems.builder()
                    .order(order)
                    .quantity(cartItem.getQuantity())
                    .productSku(cartItem.getProductSku())
                    .build();

            OrderItems createdOrderItem = orderItemService.createOrderItem(orderItem);
            order.getOrderItems().add(createdOrderItem);
        }

        return ordersRepository.save(order);
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOrderById(UUID orderId) {
        return ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
    }

}
