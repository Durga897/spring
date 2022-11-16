package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

public interface ProductDao {
    void addProduct(final Product product, final MultipartFile photo) throws IOException;

    List<Product> productList();

    Blob getImageById(final String id);

    Product getProductById(final String id);

    void updateStock(final String productId,final Integer productQuantity);

    void deleteProduct(final String productId);

    void updateProduct(final Product product);

    boolean isProductExist(final String productId);

}
