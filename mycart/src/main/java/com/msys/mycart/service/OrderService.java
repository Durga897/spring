package com.msys.mycart.service;

import com.msys.mycart.model.Order;
import com.msys.mycart.model.OrderDetails;
import com.msys.mycart.model.Product;

import java.util.List;

public interface OrderService {
    void order(final String userId);
    void buyNow(final String userId, final Product product);
    Order getCurrentOrder(final long currentOrderId);
    List<OrderDetails> fetchReceivedOrder();
    void updateOrderStatus(final long orderId, final long productId);

    List<OrderDetails> fetchOrderDetailsByUserId(final String userId);
}
