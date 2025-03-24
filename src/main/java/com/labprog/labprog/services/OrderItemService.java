package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.OrderItems;
import com.labprog.labprog.model.repositories.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    OrderItemsRepository orderItemsRepository;

    public OrderItems createOrderItem(OrderItems orderItems) {
        validate(orderItems);
        return orderItemsRepository.save(orderItems);
    }

    private void validate(OrderItems orderItems) {
        if (orderItems.getQuantity() == null || orderItems.getQuantity() <= 0) {
            throw new RuntimeException("Quantity value is invalid!");
        }

    }

}
