package com.sondevnguyen.springmicroservices.service.impl;

import com.sondevnguyen.springmicroservices.dto.ProductRequest;
import com.sondevnguyen.springmicroservices.dto.ProductResponse;
import com.sondevnguyen.springmicroservices.model.Product;
import com.sondevnguyen.springmicroservices.repository.ProductRepository;
import com.sondevnguyen.springmicroservices.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;


    @Override
    public void createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        repository.save(product);
        log.info("Product created: id - {}", product.getId());
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        var products = repository.findAll();

        if (!CollectionUtils.isEmpty(products)) {
            return products.stream()
                    .map(ProductService::mapToResponse)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
