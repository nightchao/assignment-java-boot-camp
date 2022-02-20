package com.example.shopping.product.repo;

import com.example.shopping.product.db.OrderList;
import com.example.shopping.product.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

}
