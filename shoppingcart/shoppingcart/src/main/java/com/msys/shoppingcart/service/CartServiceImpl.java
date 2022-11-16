package com.msys.shoppingcart.service;


import com.msys.shoppingcart.model.Cart;
import com.msys.shoppingcart.model.CartItem;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.repo.CartItemRepository;
import com.msys.shoppingcart.repo.CartRepository;
import com.msys.shoppingcart.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public void createCart(final User user) {
            final Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);

    }

    @Override
    public void addToCart(final CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public Cart getCartByUserId(final Long userId) {
        return cartRepository.findCartByUser_Id(userId);
    }

    @Override
    public CartItem getCartItemById(long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public List<CartItem> getAllCartItem(final Long cartId) {
        if(cartItemRepository.findAllByCart_Id(cartId)==null){
           return null;
        }
        return cartItemRepository.findAllByCart_Id(cartId);
    }

    @Override
    public CartItem getCartItemByProductIdAndCartId(final long productId, final long cartId) {
        return cartItemRepository.findByProductIdAndCart_Id(productId, cartId);
    }

    @Override
    public Long totalAmounts(final long cartId) {
        return cartItemRepository.totalAmount(cartId);
    }

    @Override
    public List<CartItem> availableCartItem(final long cartId) {
        return cartItemRepository.findByCartIdAndProductStockGreaterThan(cartId,0);
    }

    @Override
    public List<CartItem> unavailableCartItem(final long cartId) {
        return cartItemRepository.findByCartIdAndProductStockEquals(cartId,0);
    }

    @Override
    public void removeCartItem(final CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}

