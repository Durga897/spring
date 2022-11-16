package com.msys.shoppingcart.repo;

import com.msys.shoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    CartItem findById(final long id);
    List<CartItem> findAllByCart_Id(final long id);
    CartItem findByProductIdAndCart_Id(final long productId, final long cartId);
    List<CartItem> findByCartIdAndProductStockEquals(final long cartId,final Integer stock);
    List<CartItem> findByCartIdAndProductStockGreaterThan(final long cartId,final Integer stock);
    @Query("SELECT SUM(cartItem.price) FROM CartItem cartItem where cartItem.cart.id=?1 and cartItem.product.stock>0")
    long totalAmount(final long cartId);
}
