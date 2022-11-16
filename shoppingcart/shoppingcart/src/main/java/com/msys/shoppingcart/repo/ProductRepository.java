package com.msys.shoppingcart.repo;


import com.msys.shoppingcart.model.Product;
import com.msys.shoppingcart.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {

    Product findById(final Long id);

    List<Product> findAll();
    List<Product> findByStockGreaterThan(final Integer stock);

    List<Product> findAllByUser(final User user);

}
