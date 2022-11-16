package com.msys.shoppingcart.controller;

import com.msys.shoppingcart.helper.UserFoundException;
import com.msys.shoppingcart.model.Role;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody final User user) throws UserFoundException {
        return userService.saveUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody final User user) {
        return userService.updateProfile(user);
    }

    @GetMapping("/getAll")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody final Role role){
        return ResponseEntity.ok().body(userService.saveRole(role));
    }
}
