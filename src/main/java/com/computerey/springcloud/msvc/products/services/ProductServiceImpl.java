package com.computerey.springcloud.msvc.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computerey.springcloud.msvc.products.entities.Product;
import com.computerey.springcloud.msvc.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final Environment env;

    public ProductServiceImpl(ProductRepository productRepository, Environment env) {
        this.repository = productRepository;
        this.env = env;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>) repository.findAll()).stream().map(product -> {
            product.setPort(Integer.parseInt(env.getProperty("local.server.port")));    
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(product -> {
            product.setPort(Integer.parseInt(env.getProperty("local.server.port")));    
            return product;
        });
    }

}
