package com.example.shopping.product.service;

import com.example.shopping.product.db.Product;
import com.example.shopping.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getListProduct(String search) {
        Optional<List<Product>> list = productRepository.findByNameContainingIgnoreCase(search);
        return list.orElse(new ArrayList<>(1));
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
