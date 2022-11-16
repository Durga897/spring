package com.msys.mycart.repo;

import com.msys.mycart.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails,Long> {
    List<OrderDetails> findOrderDetailsByOrderStatusEquals(final String orderStatus);
    OrderDetails findByProductIdAndOrders_Id(final Long productId,final Long orderId);
    List<OrderDetails> findByOrders_User_Id(final String userId);

}
