package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.repositories.CartsRepository;
import com.labprog.labprog.model.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Carts createCart(UUID customerId) {
        logger.info("Creating new cart for customer ID: {}", customerId);

        Optional<Customers> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            Carts cart = new Carts();
            cart.setCustomer(customer.get());
            cart.setTotal(0L);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            if (customer.get().getCart() == null) {
                customer.get().setCart(cart);
            }
            return cartsRepository.save(cart);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public Carts getCartByCustomerId(UUID customerId) {
        logger.info("Getting cart for customer ID: {}", customerId);
        return cartsRepository.findByCustomer_CustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for customer"));
    }

    public Carts updateCart(UUID cartId, Carts updatedCart) {
        logger.info("Updating cart ID: {}", cartId);
        Carts existingCart = cartsRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        existingCart.setTotal(updatedCart.getTotal());
        existingCart.setUpdatedAt(LocalDateTime.now());

        return cartsRepository.save(existingCart);
    }

    public void deleteCart(UUID cartId) {
        logger.info("Deleting cart ID: {}", cartId);
        Carts cart = cartsRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cartsRepository.delete(cart);
    }
}
