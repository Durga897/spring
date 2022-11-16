package com.msys.mycart.service;

import com.msys.mycart.model.User;
import com.msys.mycart.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(final User user) {
        final Optional<User> userById = userRepository.findById(user.getId());
        final User userByEmail = userRepository.findByEmailAddress(user.getEmailAddress());
        if (Objects.isNull(userByEmail) & !userById.isPresent()) {
            final String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @Override
    public Optional<User> getUserById(final String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public boolean isValidUser(final User user) {
        final Optional<User> users=userRepository.findById(user.getId());
        return users.filter(value -> passwordEncoder.matches(user.getPassword(), value.getPassword())).isPresent();
    }

    @Override
    public void updateProfile(final User user) {
        final Optional<User> userById = userRepository.findById(user.getId());
        final User userByEmail = userRepository.findByEmailAddress(user.getEmailAddress());
        if (!Objects.isNull(userByEmail) & userById.isPresent()) {
            userByEmail.setUserName(user.getUserName());
            userByEmail.setAddress(user.getAddress());
            userByEmail.setMobileNumber(user.getMobileNumber());
            userRepository.save(userByEmail);
        }
    }
}

