package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.dao.ProductDao;
import com.msys.ecommarceApplication.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    @Override
    public void addProduct(final Product product, final MultipartFile photo) throws IOException {
            productDao.addProduct(product,photo);
    }

    @Override
    public List<Product> productList() {
        return productDao.productList();
    }

    @Override
    public Blob getImageById(final String id) {
     return productDao.getImageById(id);
    }

    @Override
    public Product getProductById(final String id) {
        return productDao.getProductById(id);
    }

    @Override
    public boolean isProductExist(String productId) {
        return productDao.isProductExist(productId);
    }

    @Override
    public void updateStock(final String productId,final Integer productQuantity) {
        productDao.updateStock(productId,productQuantity);
    }

    @Override
    public void deleteProduct(final String productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    public void updateProduct(final Product product) {
        productDao.updateProduct(product);
    }

}
