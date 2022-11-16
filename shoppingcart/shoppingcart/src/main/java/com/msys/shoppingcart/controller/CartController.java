package com.msys.shoppingcart.controller;

import com.msys.shoppingcart.model.*;
import com.msys.shoppingcart.service.CartService;
import com.msys.shoppingcart.service.ProductService;
import com.msys.shoppingcart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cart")
@Slf4j
@AllArgsConstructor
@CrossOrigin
public class CartController {
    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/addToCart/{id}")
    public Product addCartItem(@PathVariable final Long id, final CartItem cartItem, final Principal principal) {
        final Product product = productService.getProduct(id);
        final User user = userService.currentUser(principal.getName());
        final Cart cart = cartService.getCartByUserId(user.getId());
        final List<CartItem> cartList = cartService.getAllCartItem(cart.getId());
        if (cartList.isEmpty()) {
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(1);
            cartItem.setPrice(product.getPrice());
            cartService.addToCart(cartItem);
        } else {
            final CartItem cartItems = cartService.getCartItemByProductIdAndCartId(product.getId(), cart.getId());
            if (Objects.isNull(cartItems)) {
                cartItem.setProduct(product);
                cartItem.setCart(cart);
                cartItem.setQuantity(1);
                cartItem.setPrice(product.getPrice());
                cartService.addToCart(cartItem);
            } else {
                final int productQuantity = cartItems.getQuantity() + 1;
                final int productPrice = product.getPrice() * productQuantity;
                cartItems.setQuantity(productQuantity);
                cartItems.setPrice(productPrice);
                cartService.addToCart(cartItems);
            }
        }
        log.info("product added to cart successfully");
        return product;
    }

    @DeleteMapping("/delete/{cartItemId}")
    public String removeCartItem(@PathVariable("cartItemId") final long cartItemId) {
        final CartItem cartItem = cartService.getCartItemById(cartItemId);
        cartService.removeCartItem(cartItem);
        log.info("Item removed from the cart");
        return "Item removed";
    }

    @GetMapping("/getAll")
    public List<CartItem> getAllCartItems(final Principal principal) {
        final User user = userService.currentUser(principal.getName());
        final Cart cart = cartService.getCartByUserId(user.getId());
        return cartService.getAllCartItem(cart.getId());
    }

    @PutMapping("/increaseCartItemQuantity/{productId}")
    public String increaseProductQuantity(@PathVariable("productId") final long productId, final Principal principal) {
        final Product product = productService.getProduct(productId);
        final User user = userService.currentUser(principal.getName());
        final Cart cart = cartService.getCartByUserId(user.getId());
        final CartItem cartItem = cartService.getCartItemByProductIdAndCartId(productId, cart.getId());
        if (product.getStock() > 1) {
            final Integer productQuantity = cartItem.getQuantity() + 1;
            final double productPrice = product.getPrice() * productQuantity;
            cartItem.setQuantity(productQuantity);
            cartItem.setPrice(productPrice);
            cartService.addToCart(cartItem);
            log.info("Product quantity increased by 1");
            return "Product quantity increased by 1";
        }
        log.warn("product out of stock");
        return "product out of stock";
    }

    @PutMapping("/decreaseCartItemQuantity/{productId}")
    public String decreaseProductQuantity(@PathVariable("productId") final long productId, final Principal principal) {
        final Product product = productService.getProduct(productId);
        final User user = userService.currentUser(principal.getName());
        final Cart cart = cartService.getCartByUserId(user.getId());
        final CartItem cartItem = cartService.getCartItemByProductIdAndCartId(productId, cart.getId());
        if (cartItem.getQuantity() > 1) {
            final Integer productQuantity = cartItem.getQuantity() - 1;
            final double productPrice = product.getPrice() * productQuantity;
            cartItem.setQuantity(productQuantity);
            cartItem.setPrice(productPrice);
            cartService.addToCart(cartItem);
        }
        log.info("Product quantity decreased by 1");
        return "Product quantity decreased by 1";
    }
}
