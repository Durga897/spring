package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.model.User;

import java.util.List;

public interface UserService {

    int saveUser(final User user) ;

    User login(final User users);

    void deleteUser(final String emailAddress);

    User fetchUser(final String userId);

    void updateProfile(final User user);

    List<User> fetchAllUser(final User user);


}