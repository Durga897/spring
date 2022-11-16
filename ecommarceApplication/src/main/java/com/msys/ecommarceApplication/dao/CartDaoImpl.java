package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.CartItem;
import com.msys.ecommarceApplication.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class CartDaoImpl implements CartDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createCart(final String userId) {
        final Object[] sqlParameter = {userId};
        final String sqlInsertStatement = "insert into cart(userId) values(?)";
        jdbcTemplate.update(sqlInsertStatement, sqlParameter);
    }

    @Override
    public void addToCarts(final String userId, final Product product) {
        final String sql = "select id from cart where userId='" + userId + "'";
        final Integer cartId = jdbcTemplate.queryForObject(sql, Integer.class);
        final Object[] sqlParameter = {cartId, product.getId(), product.getName(), 1, product.getPrice()};
        final String sqlInsertStatement = "insert into cart_items(cartId,productId,productName,productQuantity,productPrice) values(?,?,?,?,?)";
        jdbcTemplate.update(sqlInsertStatement, sqlParameter);
    }

    @Override
    public Integer getCartId(final String userId) {
        final String sql = "select id from cart where userId='" + userId + "'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<CartItem> shoppingCartItemsList(final String userId) {
        final Integer cartId = getCartId(userId);
        return jdbcTemplate.query("SELECT * FROM cart_items where cartId='" + cartId + "'", (rs, rowNum) -> {
            final CartItem cart = new CartItem();
            cart.setId(rs.getInt("id"));
            cart.setCartId(rs.getInt("cartId"));
            cart.setProductId(rs.getString("productId"));
            cart.setProductName(rs.getString("productName"));
            cart.setProductQuantity(rs.getInt("productQuantity"));
            cart.setProductPrice(rs.getInt("productPrice"));
            return cart;
        });
    }

    @Override
    public boolean isProductExistInCart(final String productId, final Integer cartId) {
        final String sql = "SELECT * FROM cart_items WHERE productId='" + productId + "' and cartId='" + cartId + "'";
        final List<CartItem> cart = jdbcTemplate.query(sql, (rs, rowNum) ->
                new CartItem(
                        rs.getInt("id"),
                        rs.getInt("cartId"),
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getInt("productQuantity"),
                        rs.getInt("productPrice")));
        return cart.size() > 0;
    }

    @Override
    public CartItem getCartItemById(final String productId, final Integer cartId) {
        final String sql = "SELECT * FROM cart_items WHERE productId='" + productId + "' and cartId='" + cartId + "'";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new CartItem(
                        rs.getInt("id"),
                        rs.getInt("cartId"),
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getInt("productQuantity"),
                        rs.getInt("productPrice")));
    }

    public void updateShoppingCart(final Integer cartId, final String productId, final Integer productQuantity, final Integer productPrice) {
        final String sql = "UPDATE cart_items SET productQuantity=" + productQuantity + ", productPrice=" + productPrice +
                " WHERE productID = '" + productId + "' and cartId='" + cartId + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void removeShoppingCartItem(final Integer cartItemId) {
        final String SQL = "delete from cart_items where id = ?";
        jdbcTemplate.update(SQL, cartItemId);
    }

    @Override
    public void increaseProductsQuantity(final Integer cartItemId, final Integer productQuantity, final Integer productPrice) {
        final String sql = "UPDATE cart_items SET productQuantity=" + productQuantity + ", productPrice=" + productPrice + " WHERE id = '" + cartItemId + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void decreaseProductsQuantity(final Integer cartItemId, final Integer productQuantity, final Integer productPrice) {
        final String sql = "UPDATE cart_items SET productQuantity=" + productQuantity + ", productPrice=" + productPrice + " WHERE id = '" + cartItemId + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public Long totalItemsPrice(final Integer cartId) {
        final String sql = "select sum(cart_items.productPrice) from cart_items JOIN product ON cart_items.productId=product.id " +
                "where cart_items.cartId='" + cartId + "' and product.stock>0";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Long totalItems(final Integer cartId) {
        final String sql = "select sum(cart_items.productQuantity) from cart_items JOIN product ON cart_items.productId=product.id " +
                "where cart_items.cartId='" + cartId + "' and product.stock>0";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public List<CartItem> availableProduct(final String userId) {
        final Integer cartId = getCartId(userId);
        return jdbcTemplate.query("select cart_items.id,cart_items.cartId,cart_items.productId,cart_items.productName," +
                "cart_items.productQuantity,cart_items.productPrice from cart_items JOIN product ON cart_items.productId=product.id" +
                " where cart_items.cartId='" + cartId + "' and product.stock>0", (rs, rowNum) -> {
            final CartItem cart = new CartItem();
            cart.setId(rs.getInt("id"));
            cart.setCartId(rs.getInt("cartId"));
            cart.setProductId(rs.getString("productId"));
            cart.setProductName(rs.getString("productName"));
            cart.setProductQuantity(rs.getInt("productQuantity"));
            cart.setProductPrice(rs.getInt("productPrice"));
            return cart;
        });
    }

    @Override
    public List<CartItem> soldProduct(final String userId) {
        final Integer cartId = getCartId(userId);
        return jdbcTemplate.query("select cart_items.id,cart_items.cartId,cart_items.productId,cart_items.productName," +
                "cart_items.productQuantity,cart_items.productPrice from cart_items JOIN product ON cart_items.productId=product.id" +
                " where cart_items.cartId='" + cartId + "' and product.stock=0", (rs, rowNum) -> {
            final CartItem cart = new CartItem();
            cart.setId(rs.getInt("id"));
            cart.setCartId(rs.getInt("cartId"));
            cart.setProductId(rs.getString("productId"));
            cart.setProductName(rs.getString("productName"));
            cart.setProductQuantity(rs.getInt("productQuantity"));
            cart.setProductPrice(rs.getInt("productPrice"));
            return cart;
        });
    }
}