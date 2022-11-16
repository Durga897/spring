package com.msys.mycart.service;

import com.msys.mycart.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     User saveUser(final User user);
     Optional<User>getUserById(final String userId);
     boolean isValidUser(final User user);
     void updateProfile(final User user);
}
