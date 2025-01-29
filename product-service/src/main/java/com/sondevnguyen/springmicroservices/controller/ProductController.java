package com.sondevnguyen.springmicroservices.controller;

import com.sondevnguyen.springmicroservices.dto.ProductRequest;
import com.sondevnguyen.springmicroservices.dto.ProductResponse;
import com.sondevnguyen.springmicroservices.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAllProducts();
    }
}
