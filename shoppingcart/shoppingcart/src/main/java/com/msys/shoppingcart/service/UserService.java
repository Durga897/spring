package com.msys.shoppingcart.service;


import com.msys.shoppingcart.helper.UserFoundException;
import com.msys.shoppingcart.model.Role;
import com.msys.shoppingcart.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {

    User saveUser(User user) throws UserFoundException;
    Role saveRole(Role role);
    List<User> getAllUsers();
    boolean isValidUser(final User user);
    void authenticate(final String username, final String password) throws Exception;

    User updateProfile(final User user);

    User currentUser(final String username);


}
