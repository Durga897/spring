package com.msys.shoppingcart.repo;

import com.msys.shoppingcart.model.Role;
import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(String name);
}
