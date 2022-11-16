package com.msys.mycart.repo;

import com.msys.mycart.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin,String> {
    Admin findByIdAndPassword(final String id,final String password);
}
