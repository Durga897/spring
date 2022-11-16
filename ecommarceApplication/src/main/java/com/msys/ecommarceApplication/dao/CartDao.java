package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.CartItem;
import com.msys.ecommarceApplication.model.Product;

import java.util.List;

public interface CartDao {


    void createCart(final String userId);
    void addToCarts(final String userId, final Product product);

    Integer getCartId(final String userId);

    List<CartItem> shoppingCartItemsList(final String userId);

    boolean isProductExistInCart(final String productId, final Integer cartId);

    CartItem getCartItemById(final String productId, final Integer cartId);

    void updateShoppingCart(final Integer cartId, final String productId, final Integer productQuantity, final Integer productPrice) ;

    void removeShoppingCartItem(final Integer cartItemId);

    void increaseProductsQuantity(final Integer cartItemId,final Integer productQuantity, final Integer productPrice);
    void decreaseProductsQuantity(final Integer cartItemId, final Integer productQuantity, final Integer productPrice);

   Long totalItemsPrice(final Integer cartId);

   Long totalItems(final Integer cartId);
    List<CartItem>  availableProduct(final String userId);
    List<CartItem>  soldProduct(final String userId);
}
