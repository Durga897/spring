package com.msys.mycart.repo;

import com.msys.mycart.model.Product;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    Product findById(final Long id);

    List<Product> findAll();
    List<Product> findByStockGreaterThan(final Integer stock);

}
