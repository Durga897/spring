package com.msys.shoppingcart.repo;

import com.msys.shoppingcart.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails,Long> {
    OrderDetails findByProductIdAndOrders_Id(final Long productId,final Long orderId);
    List<OrderDetails> findByOrders_User_Id(final Long orders_user_id);

    List<OrderDetails> findOrderDetailsByOrderStatusEqualsAndProduct_User_Id(final String orderStatus, final Long product_user_id);

    List<OrderDetails> findOrderDetailsByProduct_User_Id(final Long product_user_id);
}
