package com.msys.shoppingcart.repo;

import com.msys.shoppingcart.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
    Order findById(final long id);
    @Query(value = "SELECT max(id) FROM Order ")
    Long currentOrderId();
}
