package com.shopping.checkout.repo;

import com.shopping.checkout.db.OrderBuy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OrderBuyRepositoryTest {

    @Autowired
    private OrderBuyRepository orderBuyRepository;

    @Test
    void findByOrderId() {
        // Arrange
        List<OrderBuy> listOrder = new ArrayList<>(1);
        OrderBuy order01 = new OrderBuy("order-test", 11, 22, 33, 44);
        OrderBuy order02 = new OrderBuy("order-test", 11, 22, 33, 44);
        listOrder.add(order01);
        listOrder.add(order02);
        orderBuyRepository.saveAll(listOrder);

        // Act
        Optional<List<OrderBuy>> result = orderBuyRepository.findByOrderId("order-test");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
    }
}