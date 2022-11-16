package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.dao.OrderDao;
import com.msys.ecommarceApplication.model.Order;
import com.msys.ecommarceApplication.model.OrderReceived;
import com.msys.ecommarceApplication.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    @Override
    public void order(final String userId) {
     orderDao.order(userId);
    }

    @Override
    public void buyNow(final String userId, final Product product) {
        orderDao.buyNow(userId,product);
    }

    @Override
    public List<Order> fetchOrderDetails(final String userId) {
        return orderDao.fetchOrderDetails(userId);
    }

    @Override
    public List<OrderReceived> fetchReceivedOrder() {
        return orderDao.fetchReceivedOrder();
    }

    @Override
    public void updateOrderStatus(final Integer orderId, final String productId) {
       orderDao.updateOrderStatus(orderId,productId);
    }
}
