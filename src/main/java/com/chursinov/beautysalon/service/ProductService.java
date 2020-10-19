package com.chursinov.beautysalon.service;


import com.chursinov.beautysalon.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByMaster(String masterName);
    List<Product> getProductsByName(String productName);

}
