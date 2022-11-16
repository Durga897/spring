package com.msys.shoppingcart.service;

import com.msys.shoppingcart.model.Role;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.repo.RoleRepository;
import com.msys.shoppingcart.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SellerServiceImpl implements SellerService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveSeller(final User user) {
        log.info("saving user into the database");
        final Role role = roleRepository.findByName("SELLER");
        final Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}