package com.msys.mycart.repo;

import com.msys.mycart.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
    Order findById(final long id);
    @Query(value = "SELECT max(id) FROM Order ")
    Long currentOrderId();
}
