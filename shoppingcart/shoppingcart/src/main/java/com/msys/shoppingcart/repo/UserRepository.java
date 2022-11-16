package com.msys.shoppingcart.repo;

import com.msys.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(final String username, final String password);
    User findByUsername(final String username);
}
