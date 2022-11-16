package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.dao.UserDao;
import com.msys.ecommarceApplication.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int saveUser(final User user) {
        final String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDao.saveUser(user);
    }

    @Override
    public User login(final User users) {
        final User user = userDao.fetchUser(users.getId());
        if(user!=null) {
          final boolean isPasswordExist = passwordEncoder.matches(users.getPassword(), user.getPassword());
          if (isPasswordExist) {
              return userDao.login(user);
          }}
        return null;
    }

    @Override
    public void deleteUser(final String emailAddress) {
        userDao.deleteUser(emailAddress);
    }

    @Override
    public User fetchUser(final String userId) {

        return userDao.fetchUser(userId);
    }

    @Override
    public void updateProfile(final User user) {
        userDao.updateProfile(user);
    }

    @Override
    public List<User> fetchAllUser(final User user) {
        return userDao.fetchAllUser(user);
    }

}
