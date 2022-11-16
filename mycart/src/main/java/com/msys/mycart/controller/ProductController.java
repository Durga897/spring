package com.msys.mycart.controller;

import com.msys.mycart.model.Product;
import com.msys.mycart.model.User;
import com.msys.mycart.service.ProductService;
import com.msys.mycart.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final Logger logger = Logger.getLogger(CartController.class);

    @PostMapping("/saveProduct")
    public String saveProduct(final Model model, final Product product, @RequestParam("photo") final MultipartFile photo) throws IOException {
        final byte[] photoBytes = photo.getBytes();
        product.setImage(photoBytes);
        productService.saveProduct(product);
        logger.info("product added successfully");
        model.addAttribute("successMessage", "product added successfully");
        return "addProduct";
    }

    @GetMapping("/findAllProducts")
    public String findProductsList(final Model model, final HttpSession session) {
        final List<Product> productList = productService.getAllAvailableProduct();
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "home";
    }

    @GetMapping("/getProduct/{id}")
    public String getProductById(final Model model, @PathVariable("id") final Long id, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        final Product product = productService.getProduct(id);
        if (product.getStock() == null || product.getStock() == 0) {
            model.addAttribute("product", product);
            return "productSold";
        }
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "productDetails";
    }

    @GetMapping("/getProductImage/{id}")
    public void getProductPhoto(final HttpServletResponse response, @PathVariable("id") final long id) throws Exception {
        response.setContentType("image/jpeg");
        final byte[] image = productService.getImageById(id);
        final InputStream inputStream = new ByteArrayInputStream(image);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/searchProduct")
    public String searchProducts(final Model model, @RequestParam("product") final String productName, final HttpSession session) {
        final String userId = (String) session.getAttribute("USERID");
        final Optional<User> user = userService.getUserById(userId);
        final List<Product> productList = productService.getAllProduct();
        final List<Product> foundedProducts = productList.stream().filter(product -> product.getName().contains(productName))
                .collect(Collectors.toList());
        if (!foundedProducts.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("productList", foundedProducts);
            return "home";
        }
        logger.error("Product Not Found");
        model.addAttribute("message", "Sorry Product Not Found");
        model.addAttribute("shopContinue", "Go To Home");
        model.addAttribute("user", user);
        return "error";

    }

    @GetMapping("/findAllProduct")
    public String findAllProduct(final Model model) {
        final List<Product> productsList = productService.getAllProduct();
        model.addAttribute("productList", productsList);
        return "viewProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(final Model model, @PathVariable final Integer id) {
        productService.deleteProduct(id);
        logger.info("product deleted successfully");
        model.addAttribute("deleteMessage", "product deleted successfully");
        return "redirect:/findAllProduct";
    }

    @GetMapping("/findProductById/{id}")
    public String findProduct(final Model model, @PathVariable("id") final long id) {
        final Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/editProduct")
    public String updateProduct(@ModelAttribute("product") final Product product) {
        logger.info("product updated successfully");
        productService.updateProduct(product);
        return "redirect:/findAllProduct";
    }

    @GetMapping("/viewAddProduct")
    public String viewPage() {
        return "addProduct";
    }
}