package com.msys.ecommarceApplication.controller;

import com.msys.ecommarceApplication.model.Admin;
import com.msys.ecommarceApplication.service.AdminService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor

public class AdminController {
    private AdminService adminService;
    private final Logger logger = Logger.getLogger(CartController.class);

    @PostMapping("/adminLogin")
    public String loginProcess(final Model model, final Admin admin, final HttpServletRequest request) {
        final Admin adminLogin = adminService.login(admin);
        if (adminLogin != null) {
            logger.info("admin login successful");
            request.getSession().setAttribute("adminId", admin.getId());
            return "adminHome";
        }
        logger.warn("Invalid Credentials");
        model.addAttribute("message", "Invalid Credentials");
        return "adminLogin";
    }
}