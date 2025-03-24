package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.CartItems;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.repositories.CartsRepository;
import com.labprog.labprog.model.repositories.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductSkuService productSkuService;

    @Autowired
    private CartItemService cartItemService;

    public Carts createCart() {

        Carts cart = new Carts();
        cart.setTotal(0L);
        cart.setCartItems(new ArrayList<>());

        return cartsRepository.save(cart);

    }

    public Carts getCart(UUID cartId) {
        return cartsRepository.findById(cartId).orElseThrow(
                () -> new RuntimeException("Cart not found!")
        );
    }

    public Carts addProductToCart(UUID cartId, UUID productSkuId, Long quantity) {

        Carts cart = cartsRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found!"));
        ProductSkus productSku = productSkuService.getProductSkuById(productSkuId);

        List<CartItems> cartItems = cart.getCartItems();
        for (CartItems _cartItem : cartItems) {
            if (_cartItem.getProductSku().getProductSkuId() == productSkuId) throw new RuntimeException("Product sku already in cart");
        }

        CartItems cartItem = cartItemService.createCartItem(productSku, quantity);

        cart.getCartItems().add(cartItem);
        cartItem.setCart(cart);
        cart.setTotal(cart.getTotal() + cartItem.getProductSku().getPrice() * quantity);

        return cartsRepository.save(cart);

    }

    public Carts removeProductFromCart(UUID cartId, UUID cartItemId) {

        Carts cart = cartsRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found!"));
        CartItems cartItem = cartItemService.getCartItem(cartItemId);

        cart.setTotal(cart.getTotal() - cartItem.getProductSku().getPrice() * cartItem.getQuantity());
        cart.getCartItems().remove(cartItem);
        cartItem.setCart(null);
        cartItemService.deleteCartItem(cartItem);

        return cartsRepository.save(cart);
    }

    public Carts clearCart(UUID cartId) {

        Carts cart = cartsRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found!"));

        List<CartItems> cartItems = cart.getCartItems();

        if (cartItems == null) throw new RuntimeException("Cart is empty");


        cart.getCartItems().clear();
        cart.setTotal(0L);

        return cartsRepository.save(cart);
    }

}
