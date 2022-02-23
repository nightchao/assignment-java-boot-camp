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
        OrderBuy order01 = new OrderBuy();
        order01.setOrderId("order-test-id");
        order01.setUserId(11);
        order01.setProductId(22);
        order01.setQuantity(33);
        order01.setPrice(100);
        order01.setName("test name 01");
        order01.setEms(false);

        OrderBuy order02 = new OrderBuy();
        order02.setOrderId("order-test-id");
        order02.setUserId(11);
        order02.setProductId(22);
        order02.setQuantity(33);
        order02.setPrice(100);
        order02.setName("test name 02");
        order02.setEms(false);

        List<OrderBuy> listOrder = new ArrayList<>(1);
        listOrder.add(order01);
        listOrder.add(order02);
        orderBuyRepository.saveAll(listOrder);

        // Act
        Optional<List<OrderBuy>> result = orderBuyRepository.findByOrderId("order-test-id");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals("order-test-id", result.get().get(0).getOrderId());
    }
}