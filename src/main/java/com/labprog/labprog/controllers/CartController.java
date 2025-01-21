package com.labprog.labprog.controllers;

import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.services.CartService;
import com.labprog.labprog.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    //Cart CRUD

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Carts> createCart(@PathVariable UUID customerId) {
        Carts cart = cartService.createCart(customerId);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Carts> getCart(@PathVariable UUID customerId) {
        Carts cart = cartService.getCartByCustomerId(customerId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Carts> updateCart(@PathVariable UUID cartId, @RequestBody Carts updatedCart) {
        Carts cart = cartService.updateCart(cartId, updatedCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable UUID cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }

    //CartItems CRUD

    @PostMapping("/customer/{customerId}/items")
    public ResponseEntity<CartItems> addCartItem(@PathVariable UUID customerId,
                                                 @RequestParam UUID productSkuId,
                                                 @RequestParam Long quantity) {
        CartItems cartItem = cartItemService.addCartItem(customerId, productSkuId, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @GetMapping("/items/{cartItemId}")
    public ResponseEntity<CartItems> getCartItem(@PathVariable UUID cartItemId) {
        CartItems cartItem = cartItemService.getCartItem(cartItemId);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItems> updateCartItem(@PathVariable UUID cartItemId,
                                                    @RequestParam Long quantity) {
        CartItems cartItem = cartItemService.updateCartItem(cartItemId, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable UUID cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
