package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);
}