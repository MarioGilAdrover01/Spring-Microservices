package com.microservices.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.products.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
