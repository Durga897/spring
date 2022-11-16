package com.msys.mycart.service;

import com.msys.mycart.model.*;
import com.msys.mycart.repo.CartItemRepository;
import com.msys.mycart.repo.CartRepository;
import com.msys.mycart.repo.OrderDetailsRepository;
import com.msys.mycart.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void order(final String userId) {
        final Cart cart = cartService.getCartByUserId(userId);
        final Optional<User> user = userService.getUserById(userId);
        final double amount = cartService.totalAmounts(cart.getId());
        final List<CartItem> list = cartService.availableCartItem(cart.getId());
        final Order order = new Order();
        if (user.isPresent()) {
            final User users = user.get();
            order.setUser(users);
            order.setAmount(amount);
            orderRepository.save(order);
        }
        for (CartItem lists : list) {
            final OrderDetails orderDetails = new OrderDetails();
            final long currentOrderId = orderRepository.currentOrderId();
            orderDetails.setOrders(getCurrentOrder(currentOrderId));
            orderDetails.setProduct(lists.getProduct());
            orderDetails.setProductAmount(lists.getPrice());
            orderDetails.setProductQuantity((long) lists.getQuantity());
            orderDetails.setOrderStatus("Placed");
            orderDetailsRepository.save(orderDetails);
        }
        final List<CartItem> cartItem = cartService.availableCartItem(cart.getId());
        cartItemRepository.deleteAll(cartItem);
    }

    @Override
    public Order getCurrentOrder(final long currentOrderId) {
        return orderRepository.findById(currentOrderId);
    }

    @Override
    public List<OrderDetails> fetchReceivedOrder() {
        return orderDetailsRepository.findOrderDetailsByOrderStatusEquals("Placed");
    }

    @Override
    public void updateOrderStatus(final long orderId,final long productId) {
      final OrderDetails orderDetails=orderDetailsRepository.findByProductIdAndOrders_Id(productId,orderId);
      orderDetails.setOrderStatus("Shipped");
      orderDetailsRepository.save(orderDetails);

    }

    @Override
    public List<OrderDetails> fetchOrderDetailsByUserId(final String userId) {
        return orderDetailsRepository.findByOrders_User_Id(userId);
    }

    @Override
    public void buyNow(final String userId, final Product product) {
        final Optional<User> user = userService.getUserById(userId);
        final double amount = product.getPrice();
        final Order order = new Order();
        if (user.isPresent()) {
            final User users = user.get();
            order.setUser(users);
            order.setAmount(amount);
            orderRepository.save(order);
        }
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
