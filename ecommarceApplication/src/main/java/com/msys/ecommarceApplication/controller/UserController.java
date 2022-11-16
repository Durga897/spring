package com.msys.ecommarceApplication.controller;

import com.msys.ecommarceApplication.model.User;
import com.msys.ecommarceApplication.service.CartService;
import com.msys.ecommarceApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final CartService cartService;
    private final Logger logger = Logger.getLogger(UserController.class);

    @PostMapping("/login")
    public String login(final Model model, final User users, final HttpServletRequest request) {
        final User user = userService.login(users);
        if (!Objects.isNull(user)) {
            request.getSession().setAttribute("USERID", user.getId());
            logger.info("Login successful");
            return "redirect:/product/getAll";
        }
        logger.error("Invalid Credential");
        model.addAttribute("alertMessage", "Invalid Credential");
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(final Model model, final User user, final HttpServletRequest request) {
        final int countUser = userService.saveUser(user);
        if (countUser == 0) {
            request.getSession().setAttribute("USERID", user.getId());
            cartService.createCart(user.getId());
            logger.info("User register successfully");
            model.addAttribute("message", "User register successfully");
            return "index";
        }
        logger.warn("user already exist");
        model.addAttribute("alertMessage", "user already exist");
        return "registrationForm";
    }

    @PostMapping("/update")
    public String updateUser(final User user) {
        userService.updateProfile(user);
        logger.info("profile updated successfully");
        return "success";
    }

    @GetMapping("/get")
    public String getUser(final Model model, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
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