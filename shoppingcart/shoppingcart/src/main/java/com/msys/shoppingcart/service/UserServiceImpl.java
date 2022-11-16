package com.msys.shoppingcart.service;

import com.msys.shoppingcart.helper.UserFoundException;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.model.Role;
import com.msys.shoppingcart.repo.RoleRepository;
import com.msys.shoppingcart.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CartService cartService;

    @Override
    public User saveUser(final User user) throws UserFoundException {
        User foundUser=userRepository.findByUsername(user.getUsername());
        if(!Objects.isNull(foundUser)){
            throw new UserFoundException();
        }
        final Role role = roleRepository.findByName("CUSTOMER");
        final Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        final User users=userRepository.save(user);
        cartService.createCart(users);
        log.info("saving user into the database");
        return users;
    }

    @Override
    public User updateProfile(final User user) {
        final User userByName = userRepository.findByUsername(user.getUsername());
         userByName.setFirstName(user.getFirstName());
         userByName.setLastName(user.getLastName());
         userByName.setAddress(user.getAddress());
         userByName.setPhone(user.getPhone());
         userByName.setEmail(user.getEmail());
         userRepository.save(userByName);
         return userByName;
    }

    @Override
    public User currentUser(final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role saveRole(final Role role) {
        log.info("saving role into the database");
        return roleRepository.save(role);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("fetching all user");
        return userRepository.findAll();
    }

    @Override
    public boolean isValidUser(final User user) {
        final User validUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        final Role role = roleRepository.findByName("CUSTOMER");
        return validUser.getRoles().equals(role);
    }
    @Override
    public void authenticate(final String username, final String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER DISABLED");
        } catch (BadCredentialsException e) {
            throw new Exception("BadCredential");
        }
    }
}