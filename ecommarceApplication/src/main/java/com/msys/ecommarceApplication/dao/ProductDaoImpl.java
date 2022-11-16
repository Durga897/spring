package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductDaoImpl implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addProduct(final Product product, final MultipartFile photo) throws IOException {
        final byte[] photoBytes = photo.getBytes();
        final Object[] sqlParameter = {product.getId(), product.getName(), product.getColor(), product.getDescription(),
                product.getPrice(),product.getStock(), photoBytes};
        final String sqlInsertStatement = "insert into product(id,name,color,description,price,stock,image) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sqlInsertStatement, sqlParameter);
    }

    @Override
    public List<Product> productList() {

        return jdbcTemplate.query("SELECT * FROM product where stock>0", (rs, rowNum) -> {
            final Product product = new Product();
            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setColor(rs.getString("color"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getInt("price"));
            product.setStock(rs.getInt("stock"));
            return product;
        });
    }

    @Override
    public Blob getImageById(final String id) {
        final String query = "select image from product where id='" + id + "'";
        return jdbcTemplate.queryForObject(query, Blob.class);
    }

    @Override
    public Product getProductById(final String id) {
        final String sql = "SELECT * FROM PRODUCT WHERE ID = '" + id + "'";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) ->
                new Product(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("color"),
                        resultSet.getInt("price"),
                        resultSet.getString("description"),
                        resultSet.getInt("stock")));
    }

    public boolean isProductExist(final String productId) {

        final String sql = "SELECT * FROM product WHERE id='" + productId + "'";
        final List<Product> products = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Product(
                       rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("color"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("stock")));
        return products.size() > 0;
    }

    @Override
    public void updateStock(final String productId, final Integer productQuantity) {
        final Product product = getProductById(productId);
        if (product.getStock() == 0) {
            final String sql = "update product set stock = '" + null + "' where id = '" + productId + "'";
            jdbcTemplate.update(sql);
        }
        final int updatedStock = product.getStock() - productQuantity;
        final String sql = "update product set stock = '" + updatedStock + "' where id = '" + productId + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteProduct(final String productId) {
        final String SQL = "delete from product where id = ?";
        jdbcTemplate.update(SQL, productId);
    }

    @Override
    public void updateProduct(final Product product) {
        final String sql = "update product set price = '" + product.getPrice() + "',stock='" + product.getStock()
                + "',color='" + product.getColor() + "',description='" + product.getDescription() + "' where id = '"
                + product.getId() + "'";
        jdbcTemplate.update(sql);
    }
}
