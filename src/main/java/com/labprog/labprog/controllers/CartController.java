package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.AddCartItemDTO;
import com.labprog.labprog.DTO.AddressesDTO;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.services.CartService;
import com.labprog.labprog.services.CartItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/addItemToCart/{cartId}")
    public ResponseEntity<Carts> addProductToCart(@PathVariable UUID cartId, @RequestBody AddCartItemDTO data) {

        Carts cart = cartService.addProductToCart(cartId, data.getProductSkuId(), data.getQuantity());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/removeItemFromCart/{cartId}/{cartItemId}")
    public ResponseEntity<Carts> removeProductFromCart(@PathVariable UUID cartId, @PathVariable UUID cartItemId) {

        Carts cart = cartService.removeProductFromCart(cartId, cartItemId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/clearCart/{cartId}")
    public ResponseEntity<Carts> clearCart(@PathVariable UUID cartId) {

        Carts cart = cartService.clearCart(cartId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/updateQuantityOfCartItem/{cartItemId}")
    public ResponseEntity<CartItems> updateCartItemQuantity(@PathVariable UUID cartItemId,
                                                    @RequestParam Long quantity) {
        CartItems cartItem = cartItemService.updateQuantityOfCartItem(cartItemId, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

}
