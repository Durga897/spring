package com.msys.mycart.controller;

import com.msys.mycart.model.Admin;
import com.msys.mycart.service.AdminService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;
    private final Logger logger = Logger.getLogger(CartController.class);

    @PostMapping("/adminLogin")
    public String loginProcess(final Model model, final Admin admin, final HttpServletRequest request) {
        final Admin adminLogin = adminService.login(admin);
        if (Objects.isNull(adminLogin)) {
            logger.warn("Invalid Credentials");
            model.addAttribute("message", "Invalid Credentials");
            return "adminLogin";
        }
        logger.info("admin login successful");
        request.getSession().setAttribute("adminId", admin.getId());
        return "adminHome";
    }
    @GetMapping("/views")
    public String views() {
        return"adminHome" ;
    }
}
