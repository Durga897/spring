package com.msys.ecommarceApplication.controller;

import com.msys.ecommarceApplication.model.CartItem;
import com.msys.ecommarceApplication.model.Product;
import com.msys.ecommarceApplication.model.User;
import com.msys.ecommarceApplication.service.CartService;
import com.msys.ecommarceApplication.service.ProductService;
import com.msys.ecommarceApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/cart")
@Controller
@AllArgsConstructor
public class CartController {
    private final ProductService productService;
    private final UserService userService;

    private final CartService cartService;
    private final Logger logger = Logger.getLogger(CartController.class);

    @PostMapping("/save/{id}")
    public String addToShoppingCart(final Model model, @PathVariable("id") final String productId, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
        final List<CartItem> cartList = cartService.shoppingCartItemsList(userId);
        final Product product = productService.getProductById(productId);
        final Integer cartId = cartService.getCartId(userId);
        if (cartList == null) {
            cartService.addToCarts(userId, product);
        } else {
            if (cartService.isProductExistInCart(productId, cartId)) {
                final CartItem cart = cartService.getCartItemById(productId, cartId);
                final Integer productQuantity = cart.getProductQuantity() + 1;
                final Integer productPrice = product.getPrice() * productQuantity;
                cartService.updateShoppingCart(cartId, productId, productQuantity, productPrice);
            } else {
                cartService.addToCarts(userId, product);
            }
        }
        model.addAttribute("user", user);
        logger.info("product added to cart successfully");
        return "redirect:/cart/getAll";
    }

    @GetMapping("/getAll")
    public String shoppingCartItemsList(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Integer cartId = cartService.getCartId(userId);
        final User user = userService.fetchUser(userId);
        final List<CartItem> cartItemList = cartService.shoppingCartItemsList(userId);
        final List<CartItem> availableCartItem = cartService.availableProduct(userId);
        final List<CartItem> soldCartItems = cartService.soldProduct(userId);

        if (cartItemList.size() > 0) {
            if (soldCartItems.size() > 0) {
                if (availableCartItem.size() == 0) {
                    model.addAttribute("message", "Your Cart is empty!");
                    model.addAttribute("status", "Saved For Later");
                    model.addAttribute("cartItem", soldCartItems);
                    return "error";
                }
                model.addAttribute("cartItems", availableCartItem);
                model.addAttribute("cartItem", soldCartItems);
                model.addAttribute("status", "Saved For Later");
                model.addAttribute("user", user);
                model.addAttribute("totalAmount", cartService.totalItemsPrice(cartId));
                return "cart";
            }
            model.addAttribute("cartItems", availableCartItem);
            model.addAttribute("user", user);
            model.addAttribute("totalAmount", cartService.totalItemsPrice(cartId));
            return "cart";
        }
        model.addAttribute("message", "Your Cart is empty!");
        return "error";
    }

    @GetMapping("/removeCartItem/{cartItemId}")
    public String removeCartItem(final Model model, @PathVariable("cartItemId") final int cartItemId, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
        cartService.removeShoppingCartItem(cartItemId);
        model.addAttribute("user", user);
        logger.info("Item removed from the cart");
        return "redirect:/cart/getAll";
    }

    @GetMapping("/increaseProductQuantity/{cartItemId}/{productId}")
    public String increaseProductQuantity(final Model model, @PathVariable("cartItemId") final int cartItemId,
                                          @PathVariable("productId") final String productId, final HttpSession session) {
        final Product product = productService.getProductById(productId);
        final String userId = (String) session.getAttribute("USERID");
        final Integer cartId = cartService.getCartId(userId);
        final User user = userService.fetchUser(userId);
        final CartItem cartItem = cartService.getCartItemById(productId, cartId);
        if (product.getStock() > 1) {
            final Integer productQuantity = cartItem.getProductQuantity() + 1;
            final Integer productPrice = product.getPrice() * productQuantity;
            cartService.increaseProductsQuantity(cartItemId, productQuantity, productPrice);
            model.addAttribute("user", user);
            logger.info("Product quantity increased by 1");
            return "redirect:/cart/getAll";
        }
        logger.info("product out of stock");
        model.addAttribute("alertMessage", "product out of stock");
        return "redirect:/cart/getAll";
    }

    @GetMapping("/decreaseProductQuantity/{cartItemId}/{productId}")
    public String decreaseProductQuantity(final Model model, @PathVariable("cartItemId") final int cartItemId,
                                          @PathVariable("productId") final String productId, final HttpSession session) {
        final Product product = productService.getProductById(productId);
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
        final Integer cartId = cartService.getCartId(userId);
        final CartItem cartItem = cartService.getCartItemById(productId, cartId);
        if (cartItem.getProductQuantity() > 1) {
            final Integer productQuantity = cartItem.getProductQuantity() - 1;
            final Integer productPrice = product.getPrice() * productQuantity;
            cartService.decreaseProductsQuantity(cartItemId, productQuantity, productPrice);
        }
        model.addAttribute("user", user);
        logger.info("Product quantity decreased by 1");
        return "redirect:/cart/getAll";
    }
}