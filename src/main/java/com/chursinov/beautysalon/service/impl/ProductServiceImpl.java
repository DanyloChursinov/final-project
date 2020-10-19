package com.chursinov.beautysalon.service.impl;

import com.chursinov.beautysalon.entity.Product;
import com.chursinov.beautysalon.repository.ProductRepository;
import com.chursinov.beautysalon.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public List<Product> getProductsByMaster(String masterName) {
        return repository.getProductsByMaster(masterName);
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        return repository.getProductsByName(productName);
    }
}
