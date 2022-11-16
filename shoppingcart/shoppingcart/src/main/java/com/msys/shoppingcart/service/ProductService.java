package com.msys.shoppingcart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.msys.shoppingcart.model.Product;

import java.security.Principal;
import java.util.List;

public interface ProductService {
    byte[] getImageById(final Long id);

    void saveProduct(final Product product);

    void deleteProduct(final Long productId);

    void updateProduct(final Product product);

    List<Product> getAllAvailableProduct();

    List<Product> getAllProduct();

    List<Product> getSellerProducts(final Principal principal);


    Product getProduct(final Long id);

    void updateStock(final Long productId, final Integer productQuantity);
    Product getJson(final String product) throws JsonProcessingException;
}

