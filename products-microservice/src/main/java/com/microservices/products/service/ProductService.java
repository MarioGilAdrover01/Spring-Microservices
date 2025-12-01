package com.microservices.products.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.products.dto.ProductRequest;
import com.microservices.products.dto.ProductResponse;
import com.microservices.products.model.Product;
import com.microservices.products.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        product = productRepository.save(product);

        log.info("Product created successfully with ID {}", product.getId());

        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> 
                    new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }

}
