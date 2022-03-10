package com.shopping.product.repo;

import com.shopping.product.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

    Optional<List<Basket>> findByUserId(int userId);
}
