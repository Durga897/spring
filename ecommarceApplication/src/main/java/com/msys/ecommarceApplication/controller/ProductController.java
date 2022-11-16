package com.msys.ecommarceApplication.controller;

import com.msys.ecommarceApplication.model.Product;
import com.msys.ecommarceApplication.model.User;
import com.msys.ecommarceApplication.service.ProductService;
import com.msys.ecommarceApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;


@RequestMapping("/product")
@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/save")
    public String addProduct(final Product product, @RequestParam("image") final MultipartFile photo,
                             final Model model) throws IOException {
        final boolean isProductExist = productService.isProductExist(product.getId());
        if (isProductExist) {
            model.addAttribute("alertMessage", "product already exist");
            return "addProduct";
        }
        productService.addProduct(product, photo);
        model.addAttribute("message", "product added successfully");
        return "addProduct";
    }

    @GetMapping("/getAll")
    public String listProduct(final Model model, final HttpSession session) {
        final List<Product> listProduct = productService.productList();
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("listProduct", listProduct);
        return "home";
    }

    @GetMapping("/getProductImage/{id}")
    public void getProductPhoto(final HttpServletResponse response, @PathVariable("id") final String id) throws Exception {
        response.setContentType("image/jpeg");
        final Blob blob = productService.getImageById(id);
        final byte[] bytes = blob.getBytes(1, (int) blob.length());
        final InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/get/{id}")
    public String getProductById(final Model model, @PathVariable("id") final String id, final HttpSession session) {
        final Product product = productService.getProductById(id);
        if (product.getStock() == null || product.getStock() == 0) {
            model.addAttribute("product", productService.getProductById(id));
            return "productSold";
        }
        final String userId = (String) session.getAttribute("USERID");
        final User user = userService.fetchUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("product", productService.getProductById(id));
        return "productDetails";
    }

    @GetMapping("/view")
    public String viewPage() {
        return "addProduct";
    }

    @GetMapping("/findAll")
    public String findAllProduct(final Model model) {
        final List<Product> productsList = productService.productList();
        model.addAttribute("productList", productsList);
        return "viewProduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(final Model model, @PathVariable final String id) {
        productService.deleteProduct(id);
        model.addAttribute("deleteMessage", "product deleted successfully");
        return "redirect:/product/findAll";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") final Product product) {
        productService.updateProduct(product);
        return "redirect:/product/findAll";
    }

    @GetMapping("/find/{id}")
    public String findProduct(final Model model, @PathVariable("id") final String id) {
        final Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }
}
