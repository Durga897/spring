package com.msys.ecommarceApplication.dao;

import com.msys.ecommarceApplication.model.User;

import java.util.List;

public interface UserDao {
    int saveUser(final User user) ;

    User login(final User user);

    void deleteUser(final String emailAddress);

    User fetchUser(final String id);

    void updateProfile(final User user) ;

    List<User> fetchAllUser(final User user);
}
