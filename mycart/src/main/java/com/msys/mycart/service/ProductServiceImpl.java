package com.msys.mycart.service;

import com.msys.mycart.model.Product;
import com.msys.mycart.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public byte[] getImageById(final Long id) {
        final Product product=productRepository.findById(id);
        return product.getImage();
    }

    @Override
    public void saveProduct(final Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(final Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void updateProduct(final Product product) {
        final Product existingProduct=productRepository.findById(product.getId());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setColor(product.getColor());
        existingProduct.setDescription(product.getDescription());
        productRepository.save(existingProduct);
    }

    @Override
    public List<Product> getAllAvailableProduct() {
        return productRepository.findByStockGreaterThan(0);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(final Long id) {
        return productRepository.findById(id);
    }
    @Override
    public void updateStock(final Long productId, final Integer productQuantity) {
        final Product product = getProduct(productId);
        if (product.getStock() == 0) {
          product.setStock(null);
        }
        final int updatedStock = product.getStock() - productQuantity;
        product.setStock(updatedStock);
        productRepository.save(product);
    }
}
