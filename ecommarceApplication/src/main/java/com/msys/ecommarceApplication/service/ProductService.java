package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

public interface ProductService {
    void addProduct(final Product product, final MultipartFile photo) throws IOException;
    List<Product> productList();
    Blob getImageById(final String id);
    Product getProductById(final String id);

    boolean isProductExist(final String productId);
    void updateStock(final String productId,final Integer productQuantity);

    void deleteProduct(final String productId);
    void updateProduct(final Product product);

}
