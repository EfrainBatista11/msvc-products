package com.computerey.springcloud.msvc.products.services;

import java.util.List;
import java.util.Optional;

import com.computerey.springcloud.msvc.products.entities.Product;

public interface ProductService {

    // Define methods for product-related operations

    List<Product> findAll();

    Optional<Product> findById(Long id);

    
}
