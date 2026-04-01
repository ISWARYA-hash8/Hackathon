package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.Product;
import com.hack.hackathon.exception.ResourceNotFoundException;
import com.hack.hackathon.repository.ProductRepository;
import com.hack.hackathon.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create product");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch products");
        }
    }

    @Override
    public Product getProductById(Long id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Product not found with id " + id));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            Product product = getProductById(id);
            productRepository.delete(product);
        } catch (Exception e) {
            throw e;
        }
    }
}