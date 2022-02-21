package com.shopping.checkout.repo;

import com.shopping.checkout.db.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    Optional<List<OrderList>> findByOrderId(String orderId);
}
