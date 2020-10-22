package com.chursinov.beautysalon.repository;

import com.chursinov.beautysalon.entity.product.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    List<Product> getProductsByMaster(String masterName);
    List<Product> getProductsByName(String productName);
}
