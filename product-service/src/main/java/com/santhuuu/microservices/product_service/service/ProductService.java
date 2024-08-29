package com.santhuuu.microservices.product_service.service;

import com.santhuuu.microservices.product_service.dto.ProductRequest;
import com.santhuuu.microservices.product_service.dto.ProductResponse;
import com.santhuuu.microservices.product_service.model.Product;
import com.santhuuu.microservices.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .decsription(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully");
        return new ProductResponse(product.getId(), product.getName(), product.getDecsription(), product.getPrice());
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDecsription(), product.getPrice()))
                .toList();
    }
}
