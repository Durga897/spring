package com.msys.shoppingcart.service;

import com.msys.shoppingcart.model.Order;
import com.msys.shoppingcart.model.OrderDetails;
import com.msys.shoppingcart.model.Product;

import java.util.List;

public interface OrderService {
    Order order(final String username);
    void buyNow(final String username, final Product product);
    Order getCurrentOrder(final long currentOrderId);

    List<OrderDetails> fetchPlacedOrder(final Long userId);
    void updateOrderStatus(final long orderId, final long productId);

    List<OrderDetails> fetchOrderDetailsByUserId(final Long userId);

    OrderDetails getOrders(final long orderId, final long productId);

    List<OrderDetails>  getOrderDetailsBySellerId(final Long sellerId);
}
