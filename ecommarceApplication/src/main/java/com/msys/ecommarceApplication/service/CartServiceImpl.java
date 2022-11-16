package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.dao.CartDao;
import com.msys.ecommarceApplication.model.CartItem;
import com.msys.ecommarceApplication.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartDao cartDao;

    @Override
    public void createCart(final String userId) {
        cartDao.createCart(userId);
    }

    @Override
    public void addToCarts(final String userId,final Product product) {
        cartDao.addToCarts(userId, product);
    }

    @Override
    public Integer getCartId(final String userId) {
        return cartDao.getCartId(userId);
    }

    @Override
    public List<CartItem> shoppingCartItemsList(final String userId) {
        return cartDao.shoppingCartItemsList(userId);
    }

    @Override
    public boolean isProductExistInCart(final String productId,final  Integer cartId) {
        return cartDao.isProductExistInCart(productId, cartId);
    }

    @Override
    public CartItem getCartItemById(final String productId, final Integer cartId) {
        return cartDao.getCartItemById(productId, cartId);
    }

    @Override
    public void updateShoppingCart(final Integer cartId, final String productId, final Integer productQuantity,
                                   final Integer productPrice) {
        cartDao.updateShoppingCart(cartId, productId, productQuantity, productPrice);
    }

    @Override
    public void removeShoppingCartItem(final int cartItemId) {
        cartDao.removeShoppingCartItem(cartItemId);
    }

    @Override
    public void increaseProductsQuantity(final int cartItemId, final Integer productQuantity,final Integer productPrice) {
        cartDao.increaseProductsQuantity(cartItemId, productQuantity, productPrice);
    }

    @Override
    public void decreaseProductsQuantity(final int cartItemId, final Integer productQuantity,final Integer productPrice) {
        cartDao.decreaseProductsQuantity(cartItemId, productQuantity, productPrice);
    }

    @Override
    public Long totalItemsPrice(final Integer cartId) {
        return cartDao.totalItemsPrice(cartId);
    }

    @Override
    public Long totalItems(final Integer cartId) {
        return cartDao.totalItems(cartId);
    }

    @Override
    public List<CartItem> availableProduct(final String userId) {
        return cartDao.availableProduct(userId);
    }

    @Override
    public List<CartItem> soldProduct(final String userId) {
        return cartDao.soldProduct(userId);
    }

}
