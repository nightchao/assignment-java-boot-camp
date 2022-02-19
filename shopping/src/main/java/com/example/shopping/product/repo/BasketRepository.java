package com.example.shopping.product.repo;

import com.example.shopping.product.db.Basket;
import com.example.shopping.product.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

}
