package com.labprog.labprog.controllers;

import com.labprog.labprog.model.entities.Orders;
import com.labprog.labprog.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/{cartId}")
    public ResponseEntity<Orders> createOrder(@PathVariable UUID cartId) {

        Orders order = orderService.save(cartId);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> findAllOrders() {
        List<Orders> orders = orderService.getAllOrders();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> findOrder(@PathVariable UUID orderId) {
        Orders order = orderService.getOrderById(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
