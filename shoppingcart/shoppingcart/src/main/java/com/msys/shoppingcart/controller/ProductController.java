package com.msys.shoppingcart.controller;


import com.msys.shoppingcart.model.Product;
import com.msys.shoppingcart.model.User;
import com.msys.shoppingcart.service.ProductService;
import com.msys.shoppingcart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @PostMapping(path = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public String saveProduct(@RequestPart final String product,@RequestPart final MultipartFile file,
                              final Principal principal) throws IOException {
        final User user = userService.currentUser(principal.getName());
        final byte[] photoBytes = file.getBytes();
        final Product products=productService.getJson(product);
        products.setImage(photoBytes);
        products.setUser(user);
        productService.saveProduct(products);
        log.info("product added successfully");
        return "product added successfully";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable final Long id) {
        productService.deleteProduct(id);
        log.info("product deleted successfully");
        return "product deleted successfully";
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody final Product product) {
        productService.updateProduct(product);
        log.info("product updated successfully");
        return product;
    }

    @GetMapping("/getAll")
    public List<Product> findProductsList() {
        return productService.getAllAvailableProduct();
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable("id") final Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/getProductImage/{id}")
    public void getProductPhoto(final HttpServletResponse response, @PathVariable("id") final long id) throws Exception {
        response.setContentType("image/jpeg");
        final byte[] image = productService.getImageById(id);
        final InputStream inputStream = new ByteArrayInputStream(image);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/search/{name}")
    public List<Product> searchProducts(@PathVariable final String name) {
        final List<Product> productList = productService.getAllProduct();
        final List<Product> foundedProducts = productList.stream().filter(product -> product.getName().contains(name))
                .collect(Collectors.toList());
        if (!foundedProducts.isEmpty()) {
            return foundedProducts;
        }
        log.error("Product Not Found");
        return null;
    }
}
