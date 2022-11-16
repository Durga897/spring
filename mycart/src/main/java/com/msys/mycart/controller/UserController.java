package com.msys.mycart.controller;

import com.msys.mycart.model.User;
import com.msys.mycart.service.CartService;
import com.msys.mycart.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final CartService cartService;

    private final Logger logger = Logger.getLogger(UserController.class);

    @PostMapping("/saveUser")
    public String saveUser(final Model model, final User user) {
        final User users = userService.saveUser(user);
        if (Objects.isNull(users)) {
            logger.warn("user already exist");
            model.addAttribute("alertMessage", "user already exist");
            return "registrationForm";
        }
        cartService.createCart(users.getId());
        logger.info("User register successfully");
        model.addAttribute("successMessage", "user register successfully");
        return "index";
    }

    @PostMapping("/login")
    public String login(final Model model, final User user,final HttpServletRequest request) {
        final boolean isValidUser = userService.isValidUser(user);
        if (isValidUser) {
            request.getSession().setAttribute("USERID", user.getId());
            logger.info("Login successful");
            return "redirect:/findAllProducts";
        }
        logger.error("Invalid Credential");
        model.addAttribute("alertMessage", "Invalid credential");
        return "index";
    }
    @PostMapping("/update")
    public String updateUser(final User user) {
        logger.info("profile updated successfully");
        userService.updateProfile(user);
        return "success";
    }
    @GetMapping("/fetchUser")
    public String getUser(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "updateProfileForm";
    }
    @GetMapping("/logout")
    public String logout(final Model model,final HttpServletRequest request){
        final HttpSession session=request.getSession();
        session.invalidate();
        logger.info("Logout successful");
        model.addAttribute("logoutMessage","logout successful");
        return "index";
    }
}