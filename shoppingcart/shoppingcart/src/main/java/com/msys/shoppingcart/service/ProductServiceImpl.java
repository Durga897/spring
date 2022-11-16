package com.msys.shoppingcart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msys.shoppingcart.model.Product;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    public byte[] getImageById(final Long id) {
        final Product product = productRepository.findById(id);
        return product.getImage();
    }

    @Override
    public void saveProduct(final Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(final Long productId) {
        final Product product=productRepository.findById(productId);
        productRepository.delete(product);
    }

    @Override
    public void updateProduct(final Product product) {
        final Product existingProduct = productRepository.findById(product.getId());
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
    public List<Product> getSellerProducts(final Principal principal) {
        final User user =  userService.currentUser(principal.getName());
        return productRepository.findAllByUser(user);
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

    public Product getJson(final String product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(product, Product.class);
    }
}
