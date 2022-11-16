package com.msys.mycart.service;

import com.msys.mycart.model.Product;

import java.util.List;

public interface ProductService {
    byte[]getImageById(final Long id);

    void saveProduct(final Product product);

    void deleteProduct(final Integer productId);
    void updateProduct(final Product product);
    List<Product> getAllAvailableProduct();
    List<Product> getAllProduct();
    Product getProduct(final Long id);
    void updateStock(final Long productId, final Integer productQuantity);

}
