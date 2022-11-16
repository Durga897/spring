package com.msys.mycart.service;

import com.msys.mycart.model.Cart;
import com.msys.mycart.model.CartItem;
import com.msys.mycart.model.Product;
import com.msys.mycart.model.User;
import com.msys.mycart.repo.CartItemRepository;
import com.msys.mycart.repo.CartRepository;
import com.msys.mycart.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void createCart(final String userId) {
        final Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            final User users = user.get();
            final Cart cart = new Cart();
            cart.setUser(users);
            cartRepository.save(cart);
        }
    }

    @Override
    public void addToCart(final CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public Cart getCartByUserId(final String userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public CartItem getCartItemById(long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public List<CartItem> getAllCartItem(final Long cartId) {
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

