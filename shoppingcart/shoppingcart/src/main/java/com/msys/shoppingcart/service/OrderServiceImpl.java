package com.msys.shoppingcart.service;

import com.msys.shoppingcart.model.*;
import com.msys.shoppingcart.repo.CartItemRepository;
import com.msys.shoppingcart.repo.OrderDetailsRepository;
import com.msys.shoppingcart.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Order order(final String username) {
        final User user = userService.currentUser(username);
        final Cart cart = cartService.getCartByUserId(user.getId());
        final double amount = cartService.totalAmounts(cart.getId());
        final List<CartItem> list = cartService.availableCartItem(cart.getId());
        final Order order = new Order();
        order.setUser(user);
        order.setAmount(amount);
        orderRepository.save(order);
        for (CartItem lists : list) {
            final OrderDetails orderDetails = new OrderDetails();
            final long currentOrderId = orderRepository.currentOrderId();
            orderDetails.setOrders(getCurrentOrder(currentOrderId));
            orderDetails.setProduct(lists.getProduct());
            orderDetails.setProductAmount(lists.getPrice());
            orderDetails.setProductQuantity((long) lists.getQuantity());
            orderDetails.setOrderStatus("Placed");
            orderDetailsRepository.save(orderDetails);
            productService.updateStock(lists.getProduct().getId(), lists.getQuantity());
        }
        final List<CartItem> cartItem = cartService.availableCartItem(cart.getId());
        cartItemRepository.deleteAll(cartItem);
        return orderRepository.save(order);
    }

    @Override
    public Order getCurrentOrder(final long currentOrderId) {
        return orderRepository.findById(currentOrderId);
    }

    @Override
    public List<OrderDetails> fetchPlacedOrder(final Long userId) {
        return orderDetailsRepository.findOrderDetailsByOrderStatusEqualsAndProduct_User_Id("Placed",userId);
    }

    @Override
    public void updateOrderStatus(final long orderId,final long productId) {
      final OrderDetails orderDetails=orderDetailsRepository.findByProductIdAndOrders_Id(productId,orderId);
      orderDetails.setOrderStatus("Shipped");
      orderDetailsRepository.save(orderDetails);

    }

    @Override
    public List<OrderDetails> fetchOrderDetailsByUserId(final Long userId) {
        return orderDetailsRepository.findByOrders_User_Id(userId);
    }

    @Override
    public OrderDetails getOrders(final long orderId,final long productId) {
          return orderDetailsRepository.findByProductIdAndOrders_Id(productId,orderId);
    }

    @Override
    public List<OrderDetails> getOrderDetailsBySellerId(final Long sellerId) {
        return orderDetailsRepository.findOrderDetailsByProduct_User_Id(sellerId);
    }

    @Override
    public void buyNow(final String username, final Product product) {
        final User user = userService.currentUser(username);
        final double amount = product.getPrice();
        final Order order = new Order();
        order.setUser(user);
        order.setAmount(amount);
        orderRepository.save(order);
        final OrderDetails orderDetails = new OrderDetails();
        final long currentOrderId = orderRepository.currentOrderId();
        orderDetails.setOrders(getCurrentOrder(currentOrderId));
        orderDetails.setProduct(product);
        orderDetails.setProductAmount(product.getPrice());
        orderDetails.setProductQuantity(1L);
        orderDetails.setOrderStatus("Placed");
        orderDetailsRepository.save(orderDetails);
    }
}
