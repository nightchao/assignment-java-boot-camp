package com.shopping.product.repo;

import com.shopping.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> findByNameContainingIgnoreCase(String name);

    Optional<Product> findById(int productId);
}
