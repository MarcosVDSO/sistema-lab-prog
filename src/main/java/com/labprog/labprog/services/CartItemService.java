package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.repositories.CartItemsRepository;
import com.labprog.labprog.model.repositories.CartsRepository;
import com.labprog.labprog.model.repositories.ProductSkusRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CartItemService.class);

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartsRepository cartsRepository;


    public CartItems createCartItem(ProductSkus productSku, Long quantity) {

        if (quantity < 0) throw new RuntimeException("Cart item quantity cannot be 0 or less");
        if (quantity > productSku.getStockQuantity()) throw new RuntimeException("Cart item quantity is greater than stock");

        CartItems cartItem = new CartItems();
        cartItem.setProductSku(productSku);
        cartItem.setQuantity(quantity);

        return cartItemsRepository.save(cartItem);
    }

    public CartItems getCartItem(UUID cartItemId) {

        return cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    public CartItems updateQuantityOfCartItem(UUID cartItemId, Long newQuantity) {

        if (newQuantity <= 0) throw new RuntimeException("Cart item quantity cannot be 0 or less");

        CartItems cartItem = cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        Carts cart = cartItem.getCart();

        if (newQuantity > cartItem.getProductSku().getStockQuantity()) throw new RuntimeException("Quantity cannot be greater than stock");

        cart.setTotal(cart.getTotal() - cartItem.getProductSku().getPrice() * cartItem.getQuantity());
        cartItem.setQuantity(newQuantity);
        cart.setTotal(cart.getTotal() + cartItem.getProductSku().getPrice() * cartItem.getQuantity());

        cartsRepository.save(cart);

        return cartItemsRepository.save(cartItem);
    }

    public void deleteCartItem(CartItems cartItem) {

        cartItemsRepository.delete(cartItem);
    }
}
