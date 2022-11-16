package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.Order;
import com.msys.ecommarceApplication.model.OrderReceived;
import com.msys.ecommarceApplication.model.Product;

import java.util.List;

public interface OrderDao {
     void order(final String userId);
     void buyNow(final String userId,final Product product);
     List<Order> fetchOrderDetails(final String userId);
     List<OrderReceived> fetchReceivedOrder();

     void updateOrderStatus(final Integer orderId,final String productId);
}
