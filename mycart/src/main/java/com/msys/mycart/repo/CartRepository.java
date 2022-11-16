package com.msys.mycart.repo;

import com.msys.mycart.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {
    Cart findByUserId(final String userId);
}
