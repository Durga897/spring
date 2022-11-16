package com.msys.shoppingcart.repo;


import com.msys.shoppingcart.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Integer> {
 Cart findCartByUser_Id(final Long user_id);
}
