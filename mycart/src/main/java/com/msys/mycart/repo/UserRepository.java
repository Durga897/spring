package com.msys.mycart.repo;

import com.msys.mycart.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<User,String> {
    User findByEmailAddress(final String emailAddress);
    Optional<User> findById(final String userId);

}
