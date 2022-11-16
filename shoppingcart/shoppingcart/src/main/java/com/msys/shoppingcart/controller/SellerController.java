package com.msys.shoppingcart.controller;

import com.msys.shoppingcart.model.Product;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.service.ProductService;
import com.msys.shoppingcart.service.SellerService;
import com.msys.shoppingcart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;
    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/save")
    public User saveSeller(@RequestBody final User user) {
        return sellerService.saveSeller(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody final User user) {return userService.updateProfile(user); }

    @GetMapping("/findProducts")
    public List<Product> SellerProduct(final Principal principal) {
        return productService.getSellerProducts(principal);
    }
}
