package com.sondevnguyen.springmicroservices.repository;

import com.sondevnguyen.springmicroservices.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
