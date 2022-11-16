package com.msys.ecommarceApplication.controller;

import com.msys.ecommarceApplication.model.*;
import com.msys.ecommarceApplication.service.CartService;
import com.msys.ecommarceApplication.service.OrderService;
import com.msys.ecommarceApplication.service.ProductService;
import com.msys.ecommarceApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping("/checkout/{productId}")
    public String checkout(final Model model, @PathVariable final String productId, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final List<CartItem> cartList = cartService.shoppingCartItemsList(userId);
        final User user = userService.fetchUser(userId);
        final Product product = productService.getProductById(productId);
        final Integer cartId = cartService.getCartId(userId);
        final List<CartItem> cartItem = new ArrayList<>();
        if (cartList == null) {
            cartService.addToCarts(userId, product);
            final CartItem cart = cartService.getCartItemById(productId, cartId);
            cartItem.add(cart);
        } else {
            if (cartService.isProductExistInCart(productId, cartId)) {
                final CartItem cart = cartService.getCartItemById(productId, cartId);
                cart.setProductQuantity(1);
                cart.setProductPrice(product.getPrice());
                cartItem.add(cart);
            } else {
                cartService.addToCarts(userId, product);
                final CartItem cart = cartService.getCartItemById(productId, cartId);
                cartItem.add(cart);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("amount", product.getPrice());
        model.addAttribute("cartItems", cartItem);
        model.addAttribute("orderProduct", "../save/" + productId);
        return "order";
    }

    @GetMapping("/checkout")
    public String checkouts(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
        final Integer cartId = cartService.getCartId(userId);
        final List<CartItem> availableCartItem = cartService.availableProduct(userId);
        model.addAttribute("user", user);
        model.addAttribute("cartItem", availableCartItem);
        model.addAttribute("amount",cartService.totalItemsPrice(cartId));
        model.addAttribute("orderProduct", "save");
        return "order";
    }

    @PostMapping("/save")
    public String order(final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final List<CartItem> cartItems = cartService.availableProduct(userId);
        for (CartItem list : cartItems) {
            productService.updateStock(list.getProductId(), list.getProductQuantity());
        }
        orderService.order(userId);
        return "orderPlaced";
    }

    @PostMapping("/save/{id}")
    public String buyNow(final HttpSession session, @PathVariable final String id) {
        final String userId = (String) session.getAttribute("USERID");
        final Product product = productService.getProductById(id);
        orderService.buyNow(userId, product);
        productService.updateStock(product.getId(), 1);
        return "orderPlaced";
    }

    @GetMapping("/getAll")
    public String fetchOrderDetails(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
        final List<Order> orderList = orderService.fetchOrderDetails(userId);
        if (orderList.size() == 0) {
            model.addAttribute("message", "Your have no orders");
            return "error";
        }
        model.addAttribute("orderList", orderList);
        model.addAttribute("user", user);
        return "orderDetails";
    }

    @GetMapping("/fetchReceivedOrder")
    public String fetchReceivedOrder(final Model model) {
        final List<OrderReceived> orderReceivedList = orderService.fetchReceivedOrder();
        model.addAttribute("orderList", orderReceivedList);
        return "ordersReceived";
    }

    @GetMapping("/toShipOrder/{orderId}/{productId}")
    public String toShipOrder(@PathVariable final Integer orderId, @PathVariable final String productId) {
        orderService.updateOrderStatus(orderId, productId);
        return "redirect:/order/fetchReceivedOrder";
    }
}