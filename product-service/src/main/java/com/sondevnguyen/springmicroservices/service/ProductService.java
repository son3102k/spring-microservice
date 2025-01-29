package com.sondevnguyen.springmicroservices.service;

import com.sondevnguyen.springmicroservices.dto.ProductRequest;
import com.sondevnguyen.springmicroservices.dto.ProductResponse;
import com.sondevnguyen.springmicroservices.model.Product;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest request);

    List<ProductResponse> findAllProducts();

    static ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
