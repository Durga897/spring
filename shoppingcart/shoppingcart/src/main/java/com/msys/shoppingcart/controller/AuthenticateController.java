package com.msys.shoppingcart.controller;

import com.msys.shoppingcart.configuration.JwtUtils;
import com.msys.shoppingcart.model.JWTRequest;
import com.msys.shoppingcart.model.JWTResponse;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.model.MyUserDetails;
import com.msys.shoppingcart.repo.UserRepository;
import com.msys.shoppingcart.service.UserDetailsServiceImpl;
import com.msys.shoppingcart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;

    private final UserService userService;


    private void authenticate(final String username, final String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER DISABLED");
        } catch (BadCredentialsException e) {
            throw new Exception("BadCredential");
        }
    }


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody final JWTRequest jwtRequest) throws Exception {

        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            throw new Exception("User not found");
        }
        final org.springframework.security.core.userdetails.UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }


    @GetMapping("/currentUser")
    public User getCurrentUser(final Principal principal){
        return userService.currentUser(principal.getName());
    }

}
