package com.msys.mycart.controller;

import com.msys.mycart.model.Cart;
import com.msys.mycart.model.CartItem;
import com.msys.mycart.model.Product;
import com.msys.mycart.model.User;
import com.msys.mycart.service.CartService;
import com.msys.mycart.service.ProductService;
import com.msys.mycart.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CartController {
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final Logger logger = Logger.getLogger(CartController.class);

    @GetMapping("/addToCart/{id}")
    public String addCartItem(@PathVariable final Long id, final HttpSession session, final CartItem cartItem) {
        final String userId = (String) session.getAttribute("USERID");
        final Product product = productService.getProduct(id);
        final Cart cart = cartService.getCartByUserId(userId);
        final List<CartItem> cartList = cartService.getAllCartItem(cart.getId());
        if (cartList.isEmpty()) {
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(1);
            cartItem.setPrice(product.getPrice());
            cartService.addToCart(cartItem);
        }else {
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
        logger.info("product added to cart successfully");
        return "redirect:/fetchShoppingCartItems";
    }

    @GetMapping("/fetchShoppingCartItems")
    public String getAllCartItems(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        final Cart cart = cartService.getCartByUserId(userId);
        final List<CartItem> cartItemList = cartService.getAllCartItem(cart.getId());
        final List<CartItem> availableCartItem = cartService.availableCartItem(cart.getId());
        final List<CartItem> soldCartItems = cartService.unavailableCartItem(cart.getId());
        if (cartItemList.size() > 0) {
            if (soldCartItems.size() > 0) {
                if (availableCartItem.size() == 0) {
                    logger.warn("Your Cart is empty!");
                    model.addAttribute("message", "Your Cart is empty!");
                    model.addAttribute("status", "Saved For Later");
                    model.addAttribute("shopContinue", "Shop Now");
                    model.addAttribute("cartItem", soldCartItems);
                    return "error";
                }
                model.addAttribute("cartItems", availableCartItem);
                model.addAttribute("cartItem", soldCartItems);
                model.addAttribute("status", "Saved For Later");
                model.addAttribute("user", user);
                model.addAttribute("totalAmount", cartService.totalAmounts(cart.getId()));
                return "cart";
            }
            model.addAttribute("cartItems", availableCartItem);
            model.addAttribute("user", user);
            model.addAttribute("totalAmount", cartService.totalAmounts(cart.getId()));
            return "cart";
        }
        logger.warn("Your Cart is empty!");
        model.addAttribute("message", "Your Cart is empty!");
        model.addAttribute("shopContinue", "Shop Now");
        return "error";
    }

    @GetMapping("/removeCartItem/{cartItemId}")
    public String removeCartItem(final Model model, @PathVariable("cartItemId") final long cartItemId, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        final CartItem cartItem = cartService.getCartItemById(cartItemId);
        cartService.removeCartItem(cartItem);
        logger.info("Item removed from the cart");
        model.addAttribute("user", user);
        return "redirect:/fetchShoppingCartItems";
    }

    @GetMapping("/increaseCartItemQuantity/{productId}")
    public String increaseProductQuantity(final Model model, @PathVariable("productId") final long productId, final HttpSession session) {
        final Product product = productService.getProduct(productId);
        final String userId = (String) session.getAttribute("USERID");
        final Cart cart = cartService.getCartByUserId(userId);
        final CartItem cartItem = cartService.getCartItemByProductIdAndCartId(productId, cart.getId());
        if (product.getStock() > 1) {
            final Integer productQuantity = cartItem.getQuantity() + 1;
            final double productPrice = product.getPrice() * productQuantity;
            cartItem.setQuantity(productQuantity);
            cartItem.setPrice(productPrice);
            cartService.addToCart(cartItem);
            logger.info("Product quantity increased by 1");
            return "redirect:/fetchShoppingCartItems";
        }
        logger.warn("product out of stock");
        model.addAttribute("alertMessage", "product out of stock");
        return "redirect:/fetchShoppingCartItems";
    }

    @GetMapping("/decreaseCartItemQuantity/{productId}")
    public String decreaseProductQuantity(@PathVariable("productId") final long productId, final HttpSession session) {
        final Product product = productService.getProduct(productId);
        final String userId = (String) session.getAttribute("USERID");
        final Cart cart = cartService.getCartByUserId(userId);
        final CartItem cartItem = cartService.getCartItemByProductIdAndCartId(productId, cart.getId());
        if (cartItem.getQuantity() > 1) {
            final Integer productQuantity = cartItem.getQuantity() - 1;
            final double productPrice = product.getPrice() * productQuantity;
            cartItem.setQuantity(productQuantity);
            cartItem.setPrice(productPrice);
            cartService.addToCart(cartItem);
        }
        logger.info("Product quantity decreased by 1");
        return "redirect:/fetchShoppingCartItems";
    }
}
