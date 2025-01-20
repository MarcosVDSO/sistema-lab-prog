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

    @Autowired
    private ProductSkusRepository productSkusRepository;

    public CartItems addCartItem(UUID customerId, UUID productSkuId, Long quantity) {
        logger.info("Adding product to cart for customer ID: {}", customerId);

        Carts cart = cartsRepository.findByCustomer_CustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for customer"));

        ProductSkus productSku = productSkusRepository.findById(productSkuId)
                .orElseThrow(() -> new RuntimeException("Product SKU not found"));

        CartItems cartItem = new CartItems();
        cartItem.setCart(cart);
        cartItem.setProductSku(productSku);
        cartItem.setQuantity(quantity);
        cartItem.setCreatedAt(LocalDateTime.now());
        cartItem.setUpdatedAt(LocalDateTime.now());

        cart.setTotal((long) (cart.getTotal() + productSku.getPrice() * quantity));
        cartsRepository.save(cart);

        return cartItemsRepository.save(cartItem);
    }

    public CartItems getCartItem(UUID cartItemId) {
        logger.info("Getting cart item ID: {}", cartItemId);
        return cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    public CartItems updateCartItem(UUID cartItemId, Long quantity) {
        logger.info("Updating cart item ID: {}", cartItemId);
        CartItems cartItem = cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItem.setQuantity(quantity);
        cartItem.setUpdatedAt(LocalDateTime.now());

        Carts cart = cartItem.getCart();
        cart.setTotal((long) (cart.getTotal() + cartItem.getProductSku().getPrice() * (quantity - cartItem.getQuantity())));
        cartsRepository.save(cart);

        return cartItemsRepository.save(cartItem);
    }

    public void deleteCartItem(UUID cartItemId) {
        logger.info("Deleting cart item ID: {}", cartItemId);
        CartItems cartItem = cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Carts cart = cartItem.getCart();
        cart.setTotal((long) (cart.getTotal() - cartItem.getProductSku().getPrice() * cartItem.getQuantity()));
        cartsRepository.save(cart);

        cartItemsRepository.delete(cartItem);
    }
}
