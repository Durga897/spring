package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.*;
import com.msys.ecommarceApplication.service.CartService;
import com.msys.ecommarceApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository

public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;
    private final CartService cartService;

    @Override
    public void order(final String userId) {
        final Integer cartId = cartService.getCartId(userId);
        final User user = userService.fetchUser(userId);
        final Long amount = cartService.totalItemsPrice(cartId);
        final List<CartItem> list = cartService.shoppingCartItemsList(userId);
        final Object[] sqlParameter = {user.getId(), user.getEmailAddress(), user.getUserName(), user.getMobileNumber(), user.getAddress(), amount};
        final String sqlInsertStatement = "insert into orders(userId,userEmail,userName,userMobile,userAddress,amount) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sqlInsertStatement, sqlParameter);
        final String sql = "select max(id) from orders";
        final Long currentOrderId = jdbcTemplate.queryForObject(sql, Long.class);
        for (CartItem lists : list) {
            final Object[] sqlOrderParameter = {currentOrderId, lists.getProductId(), lists.getProductName(),
                    lists.getProductQuantity(), lists.getProductPrice(), "placed"};
            final String sqlOrderStatement = "insert into orderdetails(orderId,productId,productName,productQuantity," +
                    "productPrice,orderStatus) values(?,?,?,?,?,?)";
            jdbcTemplate.update(sqlOrderStatement, sqlOrderParameter);
        }
        final String removeCartItems = "delete from cart_items where cartId='" + cartId + "'";
        jdbcTemplate.update(removeCartItems);
    }

    @Override
    public void buyNow(final String userId, final Product product) {
        final User user = userService.fetchUser(userId);
        final Object[] sqlParameter = {user.getId(), user.getEmailAddress(), user.getUserName(), user.getMobileNumber(),
                user.getAddress(), product.getPrice()};
        final String sqlInsertStatement = "insert into orders(userId,userEmail,userName,userMobile,userAddress,amount)" +
                                          " values(?,?,?,?,?,?)";
        jdbcTemplate.update(sqlInsertStatement, sqlParameter);
        final String sql = "select max(id) from orders";
        final Long currentOrderId = jdbcTemplate.queryForObject(sql, Long.class);
        final Object[] sqlOrderParameter = {currentOrderId, product.getId(), product.getName(), 1, product.getPrice(), "placed"};
        final String sqlOrderStatement = "insert into orderdetails(orderId,productId,productName,productQuantity,productPrice,orderStatus) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sqlOrderStatement, sqlOrderParameter);
    }

    @Override
    public List<Order> fetchOrderDetails(final String userId) {

        return jdbcTemplate.query("select orderdetails.orderId,orderdetails.productId,orderdetails.productName," +
                "orderdetails.productQuantity,orderdetails.productPrice,orderdetails.orderDate from orderdetails " +
                " JOIN orders ON orderdetails.orderId=orders.id where orders.userId='" + userId + "'", (rs, rowNum) -> {
            final Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setProductId(rs.getString("productId"));
            order.setProductName(rs.getString("productName"));
            order.setProductQuantity(rs.getInt("productQuantity"));
            order.setProductPrice(rs.getInt("productPrice"));
            order.setOrderDate(rs.getDate("orderDate"));
            return order;
        });
    }

    @Override
    public List<OrderReceived> fetchReceivedOrder() {

        return jdbcTemplate.query("select orderdetails.orderId,orderdetails.productName,orderdetails.productId," +
                "orderdetails.productQuantity,orderdetails.productPrice," +
                "orderdetails.orderDate,orderdetails.orderStatus,orders.userEmail,orders.userMobile,orders.userAddress " +
                "from orderdetails " +
                " JOIN orders ON orderdetails.orderId=orders.id where orderdetails.orderStatus='" + "placed" + "'", (rs, rowNum) -> {
            final OrderReceived order = new OrderReceived();
            order.setOrderId(rs.getInt("orderId"));
            order.setEmailAddress(rs.getString("userEmail"));
            order.setMobileNumber(rs.getLong("userMobile"));
            order.setAddress(rs.getString("userAddress"));
            order.setProductId(rs.getString("productId"));
            order.setProductName(rs.getString("productName"));
            order.setProductQuantity(rs.getInt("productQuantity"));
            order.setProductPrice(rs.getLong("productPrice"));
            order.setOrderDate(rs.getDate("orderDate"));
            order.setOrderStatus(rs.getString("orderStatus"));
            return order;
        });
    }

    @Override
    public void updateOrderStatus(final Integer orderId, final String productId) {
        jdbcTemplate.update(
                "update orderdetails set orderStatus = ? where orderId = ? and productId=?",
                "shipped", orderId, productId);
    }
}