package com.msys.mycart.service;

import com.msys.mycart.model.Cart;
import com.msys.mycart.model.CartItem;
import com.msys.mycart.model.Product;

import java.util.List;

public interface CartService {
    void createCart(final String userId);

    void addToCart(final CartItem cartItem);

    Cart getCartByUserId(final String userId);

    CartItem getCartItemById(final long id);

    List<CartItem> getAllCartItem(final Long cartId);

    CartItem getCartItemByProductIdAndCartId(final long productId, final long cartId);

    Long totalAmounts(final long cartId);

    List<CartItem> availableCartItem(final long cartId);

    List<CartItem> unavailableCartItem(final long cartId);

    void removeCartItem(final CartItem cartItem);
}
