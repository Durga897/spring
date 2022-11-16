package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.model.CartItem;
import com.msys.ecommarceApplication.model.Product;

import java.util.List;

public interface CartService {

    void createCart(final String userId);

    void addToCarts(final String userId, final Product product);

    Integer getCartId(final String userId);

    List<CartItem> shoppingCartItemsList(final String userId);

    boolean isProductExistInCart(final String productId, final Integer cartId);

    CartItem getCartItemById(final String productId, final Integer cartId);

    void updateShoppingCart(final Integer cartId, final String productId, final Integer productQuantity, final Integer productPrice);

    void removeShoppingCartItem(final int cartItemId);

    void increaseProductsQuantity(final int cartItemId, final Integer productQuantity, final Integer productPrice);

    void decreaseProductsQuantity(int cartItemId, final Integer productQuantity, final Integer productPrice);

    Long totalItemsPrice(final Integer cartId);

    Long totalItems(final Integer cartId);
    List<CartItem>  availableProduct(final String userId);
    List<CartItem>  soldProduct(final String userId);

//    void order(final Product product, final String emailAddress, final User user);
}
