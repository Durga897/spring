package com.msys.shoppingcart.controller;

import com.msys.shoppingcart.model.*;
import com.msys.shoppingcart.service.OrderService;
import com.msys.shoppingcart.service.ProductService;
import com.msys.shoppingcart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
@CrossOrigin
public class OrderController {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/fetchOrders")
    public List<OrderDetails> fetchOrderDetails(final Principal principal) {
        final User user = userService.currentUser(principal.getName());
        return orderService.fetchOrderDetailsByUserId(user.getId());
    }

    @GetMapping("/fetchPlacedOrders")
    public List<OrderDetails> fetchReceivedOrder(final Principal principal) {
        final User user=userService.currentUser(principal.getName());
        return orderService.fetchPlacedOrder(user.getId());
    }


    @GetMapping("/fetchCustomerOrders")
    public List<OrderDetails> fetchOrder(final Principal principal) {
        final User user=userService.currentUser(principal.getName());
        return orderService.getOrderDetailsBySellerId(user.getId());
    }

    @GetMapping("/get/{orderId}/{productId}")
    public OrderDetails getOrderDetailsByProductIdAndOrderId(@PathVariable final long orderId, @PathVariable final long productId){
        return orderService.getOrders(orderId,productId);
    }

    @PostMapping ("/save")
    public String order(@RequestBody final User user) {
        orderService.order(user.getUsername());
        log.info("order placed");
        return "order placed" ;
    }

    @PostMapping("/buyNow")
    public String buyNow(@RequestBody final Product product,final Principal principal) {
        orderService.buyNow(principal.getName(), product);
        productService.updateStock(product.getId(), 1);
        log.info("order placed");
        return "orderPlaced";
    }

    @GetMapping("/toShipOrder/{orderId}/{productId}")
    public String toShipOrder(@PathVariable final long orderId, @PathVariable final long productId) {
        orderService.updateOrderStatus(orderId, productId);
        log.info("order status updated");
        return "order status updated";
    }
}
