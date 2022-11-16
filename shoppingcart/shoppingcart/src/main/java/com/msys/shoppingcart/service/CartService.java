package com.msys.shoppingcart.service;


import com.msys.shoppingcart.model.Cart;
import com.msys.shoppingcart.model.CartItem;
import com.msys.shoppingcart.model.User;

import java.security.Principal;
import java.util.List;

public interface CartService {
    void createCart(final User user);

    void addToCart(final CartItem cartItem);

    Cart getCartByUserId(final Long userId);

    CartItem getCartItemById(final long id);

    List<CartItem> getAllCartItem(final Long cartId);

    CartItem getCartItemByProductIdAndCartId(final long productId, final long cartId);

    Long totalAmounts(final long cartId);

    List<CartItem> availableCartItem(final long cartId);

    List<CartItem> unavailableCartItem(final long cartId);

    void removeCartItem(final CartItem cartItem);
}
