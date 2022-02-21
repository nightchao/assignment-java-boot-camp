package com.shopping.checkout.repo;

import com.shopping.checkout.db.OrderBuy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderBuyRepository extends JpaRepository<OrderBuy, Integer> {

    Optional<List<OrderBuy>> findByOrderId(String orderId);
}
