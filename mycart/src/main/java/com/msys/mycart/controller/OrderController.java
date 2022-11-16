package com.msys.mycart.controller;

import com.msys.mycart.model.*;
import com.msys.mycart.service.CartService;
import com.msys.mycart.service.OrderService;
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
import java.util.Optional;

@Controller
@AllArgsConstructor
public class OrderController {
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final Logger logger = Logger.getLogger(CartController.class);

    @GetMapping("/checkout/{productId}")
    public String checkout(final Model model, @PathVariable final long productId, final HttpSession session,final CartItem cartItem) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        final Product product = productService.getProduct(productId);
        final Cart cart = cartService.getCartByUserId(userId);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setPrice(product.getPrice());
        cartItem.setQuantity(1);
        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItem);
        model.addAttribute("orderProduct", "../buyNow/" + productId);
        return "orders";
    }
    @GetMapping("/checkout")
    public String checkouts(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        final Cart cart = cartService.getCartByUserId(userId);
        final List<CartItem> availableCartItem = cartService.availableCartItem(cart.getId());
        model.addAttribute("user", user);
        model.addAttribute("cartItem", availableCartItem);
        model.addAttribute("amount", cartService.totalAmounts(cart.getId()));
        model.addAttribute("orderProduct", "orderProduct");
        return "order";
    }
    @GetMapping("/orderProduct")
    public String order(final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Cart cart = cartService.getCartByUserId(userId);
        final List<CartItem> cartItems = cartService.availableCartItem(cart.getId());
        for (CartItem list : cartItems) {
            productService.updateStock(list.getProduct().getId(), list.getQuantity());
        }
        orderService.order(userId);
        logger.info("order placed");
        return "orderPlaced";
    }

    @GetMapping("/buyNow/{id}")
    public String buyNow(final HttpSession session, @PathVariable final long id) {
        final String userId = (String) session.getAttribute("USERID");
        final Product product = productService.getProduct(id);
        orderService.buyNow(userId, product);
        productService.updateStock(product.getId(), 1);
        logger.info("order placed");
        return "orderPlaced";
    }
    @GetMapping("/fetchReceivedOrder")
    public String fetchReceivedOrder(final Model model) {
        final List<OrderDetails> orderReceivedList = orderService.fetchReceivedOrder();
        model.addAttribute("orderList", orderReceivedList);
        return "ordersReceived";
    }
    @GetMapping("/toShipOrder/{orderId}/{productId}")
    public String toShipOrder(@PathVariable final long orderId, @PathVariable final long productId) {
        orderService.updateOrderStatus(orderId, productId);
        logger.info("order status updated");
        return "redirect:/fetchReceivedOrder";
    }
    @GetMapping("/fetchOrders")
    public String fetchOrderDetails(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        final List<OrderDetails> orderList = orderService.fetchOrderDetailsByUserId(userId);
        if (orderList.isEmpty()) {
            logger.error("Your have no orders");
            model.addAttribute("message", "Your have no orders");
            model.addAttribute("shopContinue", "Shop Now");
            return "error";
        }
        model.addAttribute("orderList", orderList);
        model.addAttribute("user", user);
        return "orderDetails";
    }
}
