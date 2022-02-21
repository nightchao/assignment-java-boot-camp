package com.shopping.product.repo;

import com.shopping.product.db.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

}
